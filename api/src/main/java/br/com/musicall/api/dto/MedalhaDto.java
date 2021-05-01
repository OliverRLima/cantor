package br.com.musicall.api.dto;

import br.com.musicall.api.dominios.Medalha;

import java.time.LocalDate;

public class MedalhaDto extends Dto{

    private Integer idMedalha;
    private LocalDate dataInicio;
    private Boolean todasInfos;
    private Integer numPesquisas;
    private Integer numPublicacoes;
    private Integer numConvites;
    private Integer numCurtidas;

    public MedalhaDto(Medalha medalha) {
        this.idMedalha = medalha.getIdMedalha();
        this.dataInicio = medalha.getDataInicio();
        this.todasInfos = medalha.getTodasInfos();
        this.numPesquisas = medalha.getNumPesquisas();
        this.numPublicacoes = medalha.getNumPublicacoes();
        this.numConvites = medalha.getNumConvites();
        this.numCurtidas = medalha.getNumCurtidas();
    }

    public Integer getIdMedalha() {
        return idMedalha;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public Boolean getTodasInfos() {
        return todasInfos;
    }

    public Integer getNumPesquisas() {
        return numPesquisas;
    }

    public Integer getNumPublicacoes() {
        return numPublicacoes;
    }

    public Integer getNumConvites() {
        return numConvites;
    }

    public Integer getNumCurtidas() {
        return numCurtidas;
    }
}
