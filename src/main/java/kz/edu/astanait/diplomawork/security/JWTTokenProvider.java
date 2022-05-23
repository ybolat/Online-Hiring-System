package kz.edu.astanait.diplomawork.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import kz.edu.astanait.diplomawork.enviroment.JWTEnvironmentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Component
public class JWTTokenProvider {

    private final JWTEnvironmentBuilder jwtEnvironmentBuilder;

    @Autowired
    public JWTTokenProvider(JWTEnvironmentBuilder jwtEnvironmentBuilder) {
        this.jwtEnvironmentBuilder = jwtEnvironmentBuilder;
    }

    public String generateToken(UserPrincipal userPrincipal, String ipFromClient) {
        return JWT.create().withIssuer(this.jwtEnvironmentBuilder.getISSUER()).withAudience(this.jwtEnvironmentBuilder.getAUDIENCE())
                .withIssuedAt(new Date()).withSubject(userPrincipal.getUsername())
                .withClaim(this.jwtEnvironmentBuilder.getCLIENT_IP(), ipFromClient)
                .withExpiresAt(new Date(System.currentTimeMillis() + this.jwtEnvironmentBuilder.getEXPIRATION_TIME()))
                .sign(HMAC512(this.jwtEnvironmentBuilder.getSECRET().getBytes()));
    }

    public String generateTokenForCommission(String username, String ipFromClient) {
        return JWT.create().withIssuer(this.jwtEnvironmentBuilder.getISSUER()).withAudience(this.jwtEnvironmentBuilder.getAUDIENCE())
                .withIssuedAt(new Date()).withSubject(username)
                .withClaim(this.jwtEnvironmentBuilder.getCLIENT_IP(), ipFromClient)
                .withExpiresAt(new Date(System.currentTimeMillis() + this.jwtEnvironmentBuilder.getEXPIRATION_TIME()))
                .sign(HMAC512(this.jwtEnvironmentBuilder.getSECRET().getBytes()));
    }

    public String getSubject(String token) {
        JWTVerifier verifier = getJWTVerifier();
        return verifier.verify(token).getSubject();
    }

    public Authentication getAuthentication(String username, List<GrantedAuthority> authorities, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
                UsernamePasswordAuthenticationToken(username, null, authorities);
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return usernamePasswordAuthenticationToken;
    }

    public boolean isTokenValid(String username, String token, HttpServletRequest request) {
        JWTVerifier jwtVerifier = getJWTVerifier();
        Date expiration = jwtVerifier.verify(token).getExpiresAt();
        String ipFromClient = getIpFromClient(request);
        if (username != null && !expiration.before(new Date()) && jwtVerifier.verify(token)
                .getClaim(this.jwtEnvironmentBuilder.getCLIENT_IP()).asString().equals(ipFromClient)) {
            return true;
        } else {
            throw new TokenExpiredException("Token is not valid");
        }
    }

    public String getIpFromClient(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

    private JWTVerifier getJWTVerifier() {
        JWTVerifier jwtVerifier;

        try {
            Algorithm algorithm = HMAC512(this.jwtEnvironmentBuilder.getSECRET());
            jwtVerifier = JWT.require(algorithm).withIssuer(this.jwtEnvironmentBuilder.getISSUER()).build();
        }catch (JWTVerificationException e){
            throw new JWTVerificationException(this.jwtEnvironmentBuilder.getTOKEN_CANNOT_BE_VERIFIED());
        }
        return jwtVerifier;
    }
}
