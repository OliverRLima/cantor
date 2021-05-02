package br.com.musicall.api.dto;

import br.com.musicall.api.dominios.Usuario;

public class UsuarioDto {

    private Integer idUsuario;
    private String email;

    public UsuarioDto(Integer idUsuario, String email) {
        this.idUsuario = idUsuario;
        this.email = email;
    }

    public UsuarioDto(Usuario usuario) {
        this.idUsuario = usuario.getIdUsuario();
        this.email = usuario.getEmail();
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public String getEmail() {
        return email;
    }
}
