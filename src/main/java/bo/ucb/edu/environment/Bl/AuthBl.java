package bo.ucb.edu.environment.Bl;

import bo.ucb.edu.environment.Dao.RoleRepository;
import bo.ucb.edu.environment.Dao.UserRepository;
import bo.ucb.edu.environment.Dto.LoginDto;
import bo.ucb.edu.environment.Dto.TokenDto;
import bo.ucb.edu.environment.Entity.User;
import bo.ucb.edu.environment.Utils.Hash;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;


import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AuthBl {
    @Autowired
    private UserRepository loginDao;
    @Autowired
    private RoleRepository roleRepository;


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





}
