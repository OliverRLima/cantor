package br.com.musicall.api.dto;

import br.com.musicall.api.dominios.Usuario;

public class UsuarioDto extends Dto{

    private Integer idUsuario;
    private String nome;
    private String email;
    private String senha;

    public UsuarioDto(Usuario usuario) {
        this.idUsuario = usuario.getIdUsuario();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
