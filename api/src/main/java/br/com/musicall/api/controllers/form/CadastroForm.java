package br.com.musicall.api.controllers.form;

import br.com.musicall.api.dominios.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CadastroForm {

    @NotNull @NotEmpty @Length(min = 3)
    private String nome;

    @NotNull @NotEmpty @Length(min = 15)
    private String email;

    @NotNull @NotEmpty @Length(min = 8)
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
