package com.senla.ads.util;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtManager {

    Logger logger = LoggerFactory.getLogger(getClass());

    private final String jwtSecret = "senla_ads";

    public String generateToken(String login) {
        Date date = Date.from(LocalDate.now().plusDays(40).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            logger.warn("Token expired");
        } catch (UnsupportedJwtException unsEx) {
            logger.warn("Unsupported jwt");
        } catch (MalformedJwtException mjEx) {
            logger.warn("Malformed jwt");
        } catch (SignatureException sEx) {
            logger.warn("Invalid signature");
        } catch (Exception e) {
            logger.warn("invalid token");
        }
        return false;
    }

    public String getLoginFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}

