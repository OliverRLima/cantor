package br.com.musicall.api.visoes;

import br.com.musicall.api.aplicacao.respostas.LoginEnum;

public class UsuarioAutenticado {

    private Integer idUsuario;
    private String tokenAutenticacao;
    private LoginEnum estado;

    public UsuarioAutenticado(Integer idUsuario, String tokenAutenticacao, LoginEnum estado) {
        this.idUsuario = idUsuario;
        this.tokenAutenticacao = tokenAutenticacao;
        this.estado = estado;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public String getTokenAutenticacao() {
        return tokenAutenticacao;
    }

    public LoginEnum getEstado() {
        return estado;
    }

    public void alterarIdTokenEstado(Integer idUsuario, String tokenAutenticacao, LoginEnum estado){
        this.idUsuario = idUsuario;
        this.tokenAutenticacao = tokenAutenticacao;
        this.estado = estado;
    }
}
