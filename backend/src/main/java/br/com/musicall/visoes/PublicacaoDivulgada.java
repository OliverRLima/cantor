package br.com.musicall.visoes;

import java.time.LocalDate;

public class PublicacaoDivulgada {

    private Integer idPublicacao;
    private Integer idUsuario;
    private String nome;
    private String instrumento;
    private LocalDate dataAniversario;
    private String cidade;
    private String genero;
    private String publicacao;

    public PublicacaoDivulgada(Integer idPublicacao, Integer idUsuario, String nome, String instrumento, LocalDate dataAniversario, String cidade, String genero, String publicacao) {
        this.idPublicacao = idPublicacao;
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.instrumento = instrumento;
        this.dataAniversario = dataAniversario;
        this.cidade = cidade;
        this.genero = genero;
        this.publicacao = publicacao;
    }

    public PublicacaoDivulgada() {
    }

    public Integer getIdPublicacao() {
        return idPublicacao;
    }

    public void setIdPublicacao(Integer idPublicacao) {
        this.idPublicacao = idPublicacao;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(String instrumento) {
        this.instrumento = instrumento;
    }

    public LocalDate getDataAniversario() {
        return dataAniversario;
    }

    public void setDataAniversario(LocalDate dataAniversario) {
        this.dataAniversario = dataAniversario;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(String publicacao) {
        this.publicacao = publicacao;
    }
}
