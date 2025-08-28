package com.abs.auth.auth;

import com.abs.auth.security.JWTService;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.interfaces.RSAPublicKey;
import java.util.Map;

@RestController
public class JwkController {

    private final RSAPublicKey rsaPublicKey;

    public JwkController(JWTService jwtService) {
        this.rsaPublicKey = (RSAPublicKey) jwtService.getPublicKey();
    }

    @GetMapping("/.well-known/jwks.json")
    public Map<String, Object> getJwk() {
        RSAKey jwk = new RSAKey.Builder(rsaPublicKey)
                .keyID("auth-key")
                .build();
        return Map.of("keys", new Object[]{jwk.toPublicJWK().toJSONObject()});
    }

}
