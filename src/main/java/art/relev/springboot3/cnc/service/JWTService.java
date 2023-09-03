package art.relev.springboot3.cnc.service;

import com.nimbusds.jwt.SignedJWT;

public interface JWTService {
    String encode(Object data);

    String encode(Object data, Long nbf, Long exp);

    String encode(Object data, Long nbf, Long exp, Long iat);

    SignedJWT parse(String token);

    Object get(SignedJWT jwt, String name);
}