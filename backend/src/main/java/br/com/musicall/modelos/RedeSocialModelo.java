package br.com.musicall.modelos;

public class RedeSocialModelo {

    private Integer idRedeSocial;
    private String facebook;
    private String instagram;
    private String twitter;

    public RedeSocialModelo() {
    }

    public RedeSocialModelo(Integer idRedeSocial, String facebook, String instagram, String twitter) {
        this.idRedeSocial = idRedeSocial;
        this.facebook = facebook;
        this.instagram = instagram;
        this.twitter = twitter;
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

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }
}
