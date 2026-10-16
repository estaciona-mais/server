package com.estacionamais.server.security;

import java.util.Collection;
import java.util.List;

import com.estacionamais.server.model.entities.Administrador;
import com.estacionamais.server.model.entities.Cliente;
import com.estacionamais.server.model.entities.Usuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioAutenticado implements UserDetails {
    private Usuario usuario;

    public UsuarioAutenticado(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioAutenticado(Cliente cliente) {
        this.usuario = cliente.getUsuario();
    }

    public UsuarioAutenticado(Administrador administrador) {
        this.usuario = administrador.getUsuario();
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setUsuario(Cliente cliente) {
        this.usuario = cliente.getUsuario();
    }

    public void setUsuario(Administrador administrador) {
        this.usuario = administrador.getUsuario();
    }

    @Override
    public String getUsername() {
        return this.usuario.getEmail();
    }

    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String papel = "ROLE_" + usuario.getPapel().name();
        return List.of(new SimpleGrantedAuthority(papel));
    }
}
