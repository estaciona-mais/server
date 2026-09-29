package com.estacionamais.server.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiracao-minutos}")
    private Long jwtExpiracaoMinutos;

    private SecretKey chave() {
        byte[] bytes = this.jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(bytes);
    }

    public String gerarToken(UsuarioAutenticado usuarioAutenticado) {
        Date agora = new Date();
        Date expiraEm = new Date(agora.getTime() + this.jwtExpiracaoMinutos * 60 * 1000);

        return Jwts.builder()
                .subject(usuarioAutenticado.getUsuario().getId().toString())
                .claim("nome", usuarioAutenticado.getUsuario().getNome())
                .claim("papel", usuarioAutenticado.getUsuario().getPapel())
                .issuedAt(agora)
                .expiration(expiraEm)
                .signWith(chave())
                .compact();
    }

    public boolean tokenValido(String token, String emailEsperado) {
        String email = extrairEmail(token);
        return email.equals(emailEsperado) && !tokenExpirado(token);
    }

    private boolean tokenExpirado(String token) {
        Date expiraEm = extrairClaim(token, Claims::getExpiration);
        return expiraEm.before(new Date());
    }

    public String extrairEmail(String token) {
        return extrairClaim(token, Claims::getSubject);
    }

    private <T> T extrairClaim(String token, Function<Claims, T> resolver) {
        Claims claims = Jwts.parser()
                .verifyWith(chave())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return resolver.apply(claims);
    }
}
