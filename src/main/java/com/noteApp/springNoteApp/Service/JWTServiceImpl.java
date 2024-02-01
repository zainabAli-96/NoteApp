package com.noteApp.springNoteApp.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public  class JWTServiceImpl implements JWTService {
    private final UserService userService;

    public String generateToken(UserDetails userDetails) {

        return Jwts
                .builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(Map<String,Object> extraClaims, UserDetails userDetails) {

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() +604800000))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    private Key getSignInKey(){
        byte [] key = Decoders.BASE64.decode("0cf0cdc9ac11f67082b9c70c72cd6fb02d2a620d6b59e26a88b8270a1832057f");
        return Keys.hmacShaKeyFor(key);
    }
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers){

        final Claims claims =extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private Claims extractAllClaims(String JWToken){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(JWToken)
                .getBody();
    }

    public Long getUserIdFromToken(HttpServletRequest request) {
        String token = extractTokenFromRequest(request);

        // Extract the username from the token
        String username = extractUserName(token);

        // Retrieve the user ID based on the username using your user service/repository
        Long userId = userService.getUserIdByUsername(username);

        return userId;
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        // Extract the token from the request, such as from the Authorization header

        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }
    public String extractUserName (String token){
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && ! isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}
