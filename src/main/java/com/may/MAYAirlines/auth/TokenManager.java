package com.may.MAYAirlines.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class TokenManager {
    @Value("${secretKey}")
    private String SECRET_KEY;
    private static final int validity = 5*60*1000;

    private Key getKey(){
        byte[] key = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }


    private <T> T exportToken(String token, Function<Claims , T> claimsTFunction){
        final Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsTFunction.apply(claims);
    }

    public String getUsernameToken(String token){
        return exportToken(token, Claims::getSubject);
    }
    public boolean tokenControl(String token, UserDetails userDetails){
        final String username = getUsernameToken(token);
        return (username.equals(userDetails.getUsername())
                && !exportToken(token,Claims::getExpiration).before(new Date(System.currentTimeMillis())));
    }



//    public String generateToken(String username){
//
//        return  Jwts.builder()
//                .setSubject(username)
//                .setIssuer("makifyigitt")
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis()+validity))
//                .signWith(SignatureAlgorithm.HS256)
//                .compact();
//    }

//    public boolean isExpired(String token){
//        Claims claims = getClaims(token);
//        return claims.getExpiration().before(new Date(System.currentTimeMillis()));
//    }
//    private Claims getClaims(String token) {
//        return Jwts
//                .parserBuilder()
//                .setSigningKey(getKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//    public boolean tokenValidate(String token){
//        if (getUsernameToken(token)!= null && isExpired(token)){
//            return true;
//        }
//        return false;
//    }

}
