package br.com.musicall.api.dto;

public class TokenDto {

    private String token;
    private String tipo;
    private Integer idUsuario;
    private String nome;

    public TokenDto(String token, String tipo, Integer idUsuario, String nome) {
        this.token = token;
        this.tipo = tipo;
        this.idUsuario = idUsuario;
        this.nome = nome;
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

    public String getNome() {
        return nome;
    }
}
