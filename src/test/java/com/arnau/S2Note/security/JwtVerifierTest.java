package com.arnau.S2Note.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtVerifierTest {

    private JwtVerifier jwtVerifier;

    @BeforeEach
    void setup()
    {
        jwtVerifier = new JwtVerifier();
    }

    @Test
    void shouldValidateJwt() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9."
                + "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0."
                + "KMUFsIDTnFmyG3nMiGM6H9FNFUROf3wh7SmqJp-QV30";
        assertDoesNotThrow(() -> jwtVerifier.verifyJWT(token));

    }

    @Test
    void shouldGenerateAndValidateJwt() {
        String token = jwtVerifier.generateJwt("John Smith");
        assertNotNull(token);
        assertTrue(token.startsWith("eyJ"));
        assertDoesNotThrow(() -> jwtVerifier.verifyJWT(token));

    }

    @Test
    void shouldFailWithDifferentKey() {
        JwtVerifier jwtVerifier2 = new JwtVerifier("a-string-secret-at-least-256-bits-long-but-wrong");
        String token = jwtVerifier.generateJwt("John Smith");
        assertThrows(RuntimeException.class, () -> jwtVerifier2.verifyJWT(token));
    }

}