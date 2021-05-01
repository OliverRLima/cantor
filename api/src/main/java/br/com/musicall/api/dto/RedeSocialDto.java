package br.com.musicall.api.dto;

import br.com.musicall.api.dominios.RedeSocial;

public class RedeSocialDto extends Dto{

    private Integer idRedeSocial;
    private String facebook;
    private String twitter;
    private String instagram;

    public RedeSocialDto(RedeSocial redeSocial) {
        this.idRedeSocial = redeSocial.getIdRedeSocial();
        this.facebook = redeSocial.getFacebook();
        this.twitter = redeSocial.getTwitter();
        this.instagram = redeSocial.getInstagram();
    }

    public Integer getIdRedeSocial() {
        return idRedeSocial;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getInstagram() {
        return instagram;
    }
}
