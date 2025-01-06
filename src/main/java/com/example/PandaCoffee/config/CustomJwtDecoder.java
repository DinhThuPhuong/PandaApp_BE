package com.example.PandaCoffee.config;

import com.example.PandaCoffee.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.util.Objects;

@Component
public class CustomJwtDecoder implements JwtDecoder {
    //    @Value("${jwt.singerKey}")
//    private String signerKey;
    private final String KEY ="Z3lodzNuNHZqa2I1ZGtnY29vYjVjZ3Qyc2Yybms5N2F1emF5eGZzNWpjNDN4MW15bmJ1M2sxZGppZHVjcGdtN2JtZnZna3dkdXVuaDNyY3hsajRraXZkd2Z6dm9iOTZlMnZuNGU2YWFiejVvcXY3ZGN1OThib2g4NW52ZnN0cnk=";
    @Autowired
    private AuthService authService;

    private NimbusJwtDecoder nimbusJwtDecoder = null;

    @Override
    public Jwt decode(String token) throws JwtException {

        //kiem tra xem token da het han hay da bi xoa
        try {
            var response = authService.verifyToken(token);
            if (!response) throw new JwtException("Token invalid");
        } catch (JwtException e) {
            throw new JwtException("JWT verification failed: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error during token verification: " + e.getMessage(), e);
        }


        //Chi kiem tra tinh dung sai cua token
        if (Objects.isNull(nimbusJwtDecoder)) {
            SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(), "HS512");
            nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec)
                    .macAlgorithm(MacAlgorithm.HS512)
                    .build();
        }

        return nimbusJwtDecoder.decode(token);
    }
}