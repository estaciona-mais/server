package com.estacionamais.server.security;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GoogleTokenService {
    private final GoogleIdTokenVerifier verificador;
    private static final Logger log = LoggerFactory.getLogger(GoogleTokenService.class);

    public GoogleTokenService(@Value("${google.client-id}") String clientId) {
        log.debug("Client ID: " + clientId);
        this.verificador = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance())
                .setAudience(List.of(clientId))
                .build();
    }

    public DadosGoogle validar(String idToken) {
        try {
            GoogleIdToken token = this.verificador.verify(idToken);
            log.debug("Token ID: " + idToken);
            if (token == null) {
                throw new IllegalArgumentException("Token do Google inválido.");
            }

            GoogleIdToken.Payload claims = token.getPayload();

            return new DadosGoogle(
                    claims.getSubject(),
                    claims.getEmail(),
                    Boolean.TRUE.equals(claims.getEmailVerified()),
                    (String) claims.get("name"),
                    (String) claims.get("picture"));
        } catch (Exception e) {
            throw new IllegalArgumentException("Token do Google inválido.", e);
        }
    }
}
