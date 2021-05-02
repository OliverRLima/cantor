package br.com.musicall.api.dto;

public class TokenDto {

    private String token;
    private String tipo;
    private Integer idUsuario;
    private Integer idInfo;
    private Integer idSocial;

    public TokenDto(String token, String tipo, Integer idUsuario, Integer idInfo, Integer idSocial) {
        this.token = token;
        this.tipo = tipo;
        this.idUsuario = idUsuario;
        this.idInfo = idInfo;
        this.idSocial = idSocial;
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

    public Integer getIdInfo() {
        return idInfo;
    }

    public Integer getIdSocial() {
        return idSocial;
    }
}
