package io.jose827corrza.github.pepepizza.web.configuration;

import ch.qos.logback.core.util.TimeUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component // To include it into the Spring lifecycle
public class JwtUtil {
    private static String SECRET_KEY = "p3p3_p1zz4";
    private static Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

    public String create(String username){
        return JWT.create()
                .withSubject(username)
                .withIssuer("pepe-pizza")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(2)))
                .sign(ALGORITHM);
    }

    public boolean isValid(String token) {
        try {
            JWT.require(ALGORITHM)
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public String getUserName(String token) {
        return JWT.require(ALGORITHM)
                .build()
                .verify(token)
                .getSubject();
    }
}
