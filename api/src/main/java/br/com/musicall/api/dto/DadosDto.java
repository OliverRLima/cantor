package br.com.musicall.api.dto;

import java.time.LocalDate;

public class DadosDto {

    private String nome;
    private LocalDate dataAniversario;
    private String estado;
    private String cidade;
    private String facebook;
    private String instagram;
    private String telefone;
    private String instrumento;
    private String generoMusical;

    public DadosDto(String nome, LocalDate dataAniversario, String estado, String cidade, String facebook, String instagram, String telefone, String instrumento, String generoMusical) {
        this.nome = nome;
        this.dataAniversario = dataAniversario;
        this.estado = estado;
        this.cidade = cidade;
        this.facebook = facebook;
        this.instagram = instagram;
        this.telefone = telefone;
        this.instrumento = instrumento;
        this.generoMusical = generoMusical;
    }

    public DadosDto(LocalDate dataAniversario, String estado, String cidade, String facebook, String instagram, String telefone, String instrumento, String generoMusical) {
        this.dataAniversario = dataAniversario;
        this.estado = estado;
        this.cidade = cidade;
        this.facebook = facebook;
        this.instagram = instagram;
        this.telefone = telefone;
        this.instrumento = instrumento;
        this.generoMusical = generoMusical;
    }

    public DadosDto() {
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataAniversario() {
        return dataAniversario;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
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

    public String getInstrumento() {
        return instrumento;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }
}
