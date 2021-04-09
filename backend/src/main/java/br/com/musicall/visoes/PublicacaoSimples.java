package br.com.musicall.visoes;

import java.time.LocalDate;

public class PublicacaoSimples {

    private String nome;
    private String email;
    private String publicacao;
    private LocalDate data;

    public PublicacaoSimples(String nome, String email, String publicacao, LocalDate data) {
        this.nome = nome;
        this.email = email;
        this.publicacao = publicacao;
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getPublicacao() {
        return publicacao;
    }

    public LocalDate getData() {
        return data;
    }
}
