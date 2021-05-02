package br.com.musicall.api.controllers.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SocialForm {

    @NotNull @NotEmpty @Length(min = 8)
    private String facebook;

    @NotNull @NotEmpty @Length(min = 8)
    private String instagram;

    @NotNull @NotEmpty @Length(min = 8)
    private String telefone;

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
