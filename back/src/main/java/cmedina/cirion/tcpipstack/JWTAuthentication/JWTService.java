package cmedina.cirion.tcpipstack.JWTAuthentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import org.springframework.core.DecoratingClassLoader;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JWTService {

    private static final String SECRET_KEY = "8a79cc0d08e62a9a8a1f08d6953dc6683198f8d831c5c28a389dd347b5edff43"; // >= 256 bits

    public String getToken(UserDetails usuario) {
        return getNewToken(new HashMap<>(), usuario);
    }

    private String getNewToken(Map<String, Object> extraclaims, UserDetails usuario) {
        return Jwts.builder()
                .setClaims(extraclaims)
                .setSubject(usuario.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey() {
        byte[] KeysArray = Decoders.BASE64.decode((SECRET_KEY));
        return Keys.hmacShaKeyFor(KeysArray);
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return(username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims getAllClaims(String token){
        return Jwts
                .parser()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpirationDate(String token){
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String Token){
        return getExpirationDate(Token).before(new Date());
    }
}
