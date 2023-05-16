package bo.ucb.edu.environment.Bl;

import bo.ucb.edu.environment.Dao.LoginDao;
import bo.ucb.edu.environment.Dto.LoginDto;
import bo.ucb.edu.environment.Dto.TokenDto;
import bo.ucb.edu.environment.Entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;


import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import bo.ucb.edu.environment.Repository.UserRepository;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class AuthBl {
    @Autowired
    private LoginDao loginDao;

    public static String KEY = "barcelonaCampeon2023";


    public TokenDto login(LoginDto login) {
        System.out.println(login.getEmail() + " " + login.getSecret());
        User user = loginDao.findByEmailAndSecret(login.getEmail(), login.getSecret());
        if (user != null) {
            TokenDto tokenDto = new TokenDto();
            tokenDto.setAuthToken(generateToken(user.getUserId(), user.getEmail(), "AUTH", 30));
            tokenDto.setRefreshToken(generateToken(user.getUserId(), user.getEmail(), "REFRESH", 60));
            return tokenDto;
        } else {
            return null;
        }

    }
    private String generateToken(Long userId, String email, String type, int minutes) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(KEY);
            String token = JWT.create()
                    .withIssuer("www.ucb.edu.bo")
                    .withClaim("userId", userId)
                    .withClaim("type", type)
                    .withClaim("name", email)
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

    public static int getUserIdFromToken(String jwt)  {
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

    public static String getTokenFromHeader(Map<String, String> headers) throws Exception {
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
