package br.com.musicall.visoes;

public class UsuarioAutenticado {

    private Integer idUsuario;
    private String token;
    private Integer idInfoUsuario;
    private Integer idRedeSocial;

    public UsuarioAutenticado() {
    }

    public UsuarioAutenticado(Integer idUsuario, String token, Integer idInfoUsuario, Integer idRedeSocial) {
        this.idUsuario = idUsuario;
        this.token = token;
        this.idInfoUsuario = idInfoUsuario;
        this.idRedeSocial = idRedeSocial;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getIdInfoUsuario() {
        return idInfoUsuario;
    }

    public void setIdInfoUsuario(Integer idInfoUsuario) {
        this.idInfoUsuario = idInfoUsuario;
    }

    public Integer getIdRedeSocial() {
        return idRedeSocial;
    }

    public void setIdRedeSocial(Integer idRedeSocial) {
        this.idRedeSocial = idRedeSocial;
    }
}
