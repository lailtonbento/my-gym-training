package br.com.mygymtraining.controller.config.security;

import br.com.mygymtraining.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtService {

    // @Value("${api.security.token.SECRET_KEY}")
    private String SECRET_KEY = "KX=,dzX_1cPqlpu+UEsXUqu-KQ[}NQ^F*KmWvVmaC43`QLHG!0s;TX5lO(.c_";

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                    .withIssuer("My Gym Training")
                    .withSubject(user.getEmail())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException ex) {
            throw new RuntimeException("Error while generating Token", ex);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.require(algorithm)
                    .withIssuer("My Gym Training")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException ex) {
            return "";
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.of("-03:00"));
    }
}
