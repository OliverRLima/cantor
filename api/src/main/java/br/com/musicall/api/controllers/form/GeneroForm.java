package br.com.musicall.api.controllers.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class GeneroForm {

    @NotNull @NotEmpty
    private String generoMusical;

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }
}
