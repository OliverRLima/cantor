package br.com.musicall.api.dto;

public class TokenDto {

    private String token;
    private String tipo;
    private Integer idUsuario;

    public TokenDto(String token, String tipo, Integer idUsuario) {
        this.token = token;
        this.tipo = tipo;
        this.idUsuario = idUsuario;
    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }
}
