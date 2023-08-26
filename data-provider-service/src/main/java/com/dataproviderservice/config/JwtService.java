package com.dataproviderservice.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Service
public class JwtService {


    UserDetails userDetails;
    private static final String SECURITY_KEY ="emTTPHMuoK0eBWQpIR9sow9NnWq8wWPUt3nBP0+bDT1GVUf39VxJOXcmDsMY6/F8QsjuNUW+9+7heTBv7pK/4m4Hq1hB/AG1LEUnc4jP85RQsH83CUlBmiFscFcljkSEgkeMSD7AxSu1WBq0LE97JMSAVEVBZ0MM/lAdHn+rU5RKwt4SghMGZt7Mlxz0EYUqEGaKiHyKiOKIps1WEiTCwq+Q3mfwPl9VrjdYr5mA28UvPJbvEAlMK56wu3oAlC4FVMYRTIRr4eIf5MCp7tE9dEmR+iOrdilawMhU+87Lljh4rGpOA627AdUim1oLm/ki6WwBlj5Vu6l4gGKZhSITFP40khnIXlVPnrCm9P2bfYw=" ;

    public String extractUserName(String token)
    {
      return   extractClaims(token,Claims::getSubject);
    }
    private Claims extractAllClaims(String token)
    {
        try {
            return Jwts
                    .parserBuilder()  //use for parsing data
                    .setSigningKey(SECURITY_KEY)  //setting key
                    .build()          //for building object
                    .parseClaimsJws(token).getBody();
        }
       catch (Exception e){
           System.out.println(e);
           return null;
       }
    }
    private Key getSigningKey()
    {
        byte[] keyBytes= Decoders.BASE64.decode(SECURITY_KEY);
        return Keys.hmacShaKeyFor(keyBytes);  //hmacshakey is algorithm which is use for keys
    }
    public <T> T extractClaims(String token, Function<Claims,T> claimsResolver)
    {
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails)
    {
        return generateToken(new HashMap<>(),userDetails);

    }
    public String generateToken(Map<String,Object>extractClaims,UserDetails userDetails)
    {
        return Jwts.builder()
                .setClaims(extractClaims)
                .setExpiration(new Date(System.currentTimeMillis()*1000 *3*60))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(userDetails.getUsername())
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)

                .compact(); //compact method generate string to9ken
    }


    //this method can validate a token that can came with api
    public boolean isTokenValid(String token,UserDetails userDetails)
    {
        final String username=extractUserName(token);
        return (username.equals(userDetails.getUsername()) &&!isTokenExpired(token));
    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    //this method get expiration date from claims
    private Date extractExpiration(String token) {
       return extractClaims(token,Claims::getExpiration);
    }

}
