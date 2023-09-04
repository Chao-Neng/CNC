package art.relev.springboot3.cnc.service.impl;

import art.relev.springboot3.cnc.service.JWTService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JWTServiceImpl implements JWTService {
    private final RSAKey rsaKey;
    private final JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.RS256)
            .type(JOSEObjectType.JWT)
            .build();

    @SneakyThrows
    @Override
    public String encode(Object data) {
        Long now = new Date().getTime() / 1000;
        return encode(data, now, now + 24 * 60 * 60, now);
    }

    @SneakyThrows
    @Override
    public String encode(Object data, Long nbf, Long exp) {
        Long now = new Date().getTime() / 1000;
        return encode(data, nbf, exp, now);
    }

    @SneakyThrows
    @Override
    public String encode(Object data, Long nbf, Long exp, Long iat) {
        Map<String, Object> map = new ObjectMapper().convertValue(data, new TypeReference<>() {
        });
        JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
        map.put("iss", "cnc");
        map.put("iat", iat);
        map.put("nbf", nbf);
        map.put("exp", exp);
        map.forEach(builder::claim);
        JWTClaimsSet claimsSet = builder.build();
        SignedJWT jwt = new SignedJWT(jwsHeader, claimsSet);
        JWSSigner jwsSigner = new RSASSASigner(rsaKey);
        jwt.sign(jwsSigner);
        return jwt.serialize();
    }

    @Override
    public SignedJWT parse(String token) {
        if (token == null) {
            return null;
        }
        try {
            SignedJWT jwt = SignedJWT.parse(token);
            JWSVerifier jwsVerifier = new RSASSAVerifier(rsaKey);
            if (jwt.verify(jwsVerifier)) {
                JWTClaimsSet claimsSet = jwt.getJWTClaimsSet();
                Date exp = claimsSet.getExpirationTime();
                Date nbf = claimsSet.getNotBeforeTime();
                Date now = new Date();
                // TODO: 尝试自动续期
                if (now.after(nbf) && now.before(exp)) {
                    return jwt;
                }
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    @Override
    public Object get(SignedJWT jwt, String name) {
        try {
            JWTClaimsSet claimsSet = jwt.getJWTClaimsSet();
            return claimsSet.getClaim(name);
        } catch (ParseException e) {
            return null;
        }
    }
}