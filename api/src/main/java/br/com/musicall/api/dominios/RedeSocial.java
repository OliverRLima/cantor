package br.com.musicall.api.dominios;

import br.com.musicall.api.controllers.form.SocialForm;

import javax.persistence.*;

@Entity
public class RedeSocial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRedeSocial;

    @Column(nullable = true, length = 255)
    private String facebook;

    @Column(nullable = true, length = 255)
    private String twitter;

    @Column(nullable = true, length = 255)
    private String instagram;

    public RedeSocial() {
    }

    public RedeSocial(Integer idRedeSocial, String facebook, String twitter, String instagram) {
        this.idRedeSocial = idRedeSocial;
        this.facebook = facebook;
        this.twitter = twitter;
        this.instagram = instagram;
    }

    public RedeSocial(SocialForm form) {
        this.facebook = form.getFacebook();
        this.twitter = form.getTelefone();
        this.instagram = form.getInstagram();
    }

    public Integer getIdRedeSocial() {
        return idRedeSocial;
    }

    public void setIdRedeSocial(Integer idRedeSocial) {
        this.idRedeSocial = idRedeSocial;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }
}
