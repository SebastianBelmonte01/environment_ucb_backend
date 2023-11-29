package bo.ucb.edu.environment.Bl;

import bo.ucb.edu.environment.Dao.RoleRepository;
import bo.ucb.edu.environment.Dao.UserRepository;
import bo.ucb.edu.environment.Dao.VerificationCodeRepository;
import bo.ucb.edu.environment.Dto.ChangePasswordDto;
import bo.ucb.edu.environment.Dto.LoginDto;
import bo.ucb.edu.environment.Dto.TokenDto;
import bo.ucb.edu.environment.Dto.UserDto;
import bo.ucb.edu.environment.Entity.User;
import bo.ucb.edu.environment.Entity.VerificationCode;
import bo.ucb.edu.environment.Utils.Hash;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;


import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthBl {
    @Autowired
    private UserRepository loginDao;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;

    @Autowired
    private JavaMailSender mailSender;



    public static String KEY = "barcelonaCampeon2023";


    public TokenDto login(LoginDto login) {
        Hash hash = new Hash();
        System.out.println("HASHED");
        System.out.println(hash.hashString(login.getSecret(), login.getEmail()));
        User user = loginDao.findByEmailAndSecret(login.getEmail(), hash.hashString(login.getSecret(), login.getEmail()));
        if (user != null) {
            //ROLES OF USER
            List<String> roles = roleRepository.findAllRolesByUserId(user.getUserId());

            TokenDto tokenDto = new TokenDto();
            tokenDto.setAuthToken(generateToken(user.getUserId(), user.getEmail(), "AUTH", 30, roles));
            tokenDto.setRefreshToken(generateToken(user.getUserId(), user.getEmail(), "REFRESH", 60, roles));
            return tokenDto;
        } else {
            return null;
        }

    }
    private String generateToken(Long userId, String email, String type, int minutes, List<String> roles) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(KEY);
            String token = JWT.create()
                    .withIssuer("www.ucb.edu.bo")
                    .withClaim("userId", userId)
                    .withClaim("type", type)
                    .withClaim("email", email)
                    .withClaim("isAdmin", roles.contains("Atender reclamos") ? true : false)
                    .withArrayClaim("roles", roles.toArray(new String[roles.size()]))
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * minutes)) // 24 horas
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            System.out.println("Error al generar el token " + userId + " " + email + " " + type + " " + minutes);
            throw new RuntimeException("Error al generar el token", exception);
        }
    }

    public boolean validateToken(String token) {
        if(token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256(KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    // specify an specific claim validations
                    .withIssuer("www.ucb.edu.bo")
                    // reusable verifier instance
                    .build();
            decodedJWT = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception){
            System.err.print("Token invalido: " + exception.getMessage());
            return false;
        }
    }

    public int getUserIdFromToken(String jwt)  {
        int userId;
        try {
            userId = JWT.require(Algorithm.HMAC256(KEY))
                    .build()
                    .verify(jwt)
                    .getClaim("userId")
                    .asInt();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("JWT no valido");
        }
        return userId;
    }

    public String getTokenFromHeader(Map<String, String> headers) throws Exception {
        if(headers.get("Authorization") == null &&
                headers.get("authorization") == null) {
            throw new Exception("No token provided");
        }

        String jwt;
        if(headers.get("Authorization") != null) {
            jwt = headers.get("Authorization").split(" ")[1];
        } else {
            jwt = headers.get("authorization").split(" ")[1];
        }

        if(jwt == null || jwt.isEmpty()) {
            throw new Exception("Missing token.");
        }
        return jwt;
    }

    public boolean forgotPassword(LoginDto login) {
        User user = loginDao.findByEmail(login.getEmail());
        if (user != null) {
            // Generar un UUID y un codigo de 4 digitos y guardarlo en la tabla verification_code
            String uuid = UUID.randomUUID().toString();
            Random random = new Random();
            int randomNumber = random.nextInt(9999);
            Long longRandomNumber = Long.valueOf(randomNumber);
            VerificationCode verificationCode = new VerificationCode();
            verificationCode.setVcUuid(uuid);
            verificationCode.setCode(longRandomNumber);
            verificationCode.setUserId(user.getUserId());
            verificationCode.setTxDate(new Date());
            verificationCode.setStatus(true);
            verificationCodeRepository.save(verificationCode);
            // Enviar correo electrónico al usuario con un codigo
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Recuperación de contraseña");
            message.setText("Para" + user.getEmail() + "! Tu codigo de recuperacion de contrasena es: " + verificationCode.getCode());
            mailSender.send(message);
            return true;
        } else {
            return false;
        }
    }




    public boolean changePassword(ChangePasswordDto changePasswordDto) {
        Hash hash = new Hash();
        User user = loginDao.findByEmail(changePasswordDto.getEmail());
        String passwordHash = hash.hashString(changePasswordDto.getNewPassword(), changePasswordDto.getEmail());
        if (user != null) {
            User userUpdated = loginDao.updatePasswordByUserId(user.getUserId(), passwordHash);
            if(userUpdated != null) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }



}
