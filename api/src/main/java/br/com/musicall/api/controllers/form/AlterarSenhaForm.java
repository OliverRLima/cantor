package br.com.musicall.api.controllers.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AlterarSenhaForm {

    @NotEmpty @NotNull @Length(min = 8, max = 40)
    private String senha;

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }
}
