package br.com.musicall.api.dto;

import br.com.musicall.api.dominios.RedeSocial;

public class RedeSocialDto {

    private Integer idRedeSocial;
    private String facebook;
    private String instagram;
    private String telefone;

    public RedeSocialDto(RedeSocial redeSocial) {
        this.idRedeSocial = redeSocial.getIdRedeSocial();
        this.facebook = redeSocial.getFacebook();
        this.instagram = redeSocial.getInstagram();
        this.telefone = redeSocial.getTwitter();
    }

    public Integer getIdRedeSocial() {
        return idRedeSocial;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public String getTelefone() {
        return telefone;
    }
}
