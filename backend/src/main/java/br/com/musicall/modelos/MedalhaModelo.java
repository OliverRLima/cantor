package br.com.musicall.modelos;

import java.time.LocalDate;

public class MedalhaModelo {

    private Integer idMedalha;
    private LocalDate dataInicio;
    private boolean todasInfos;
    private Integer numPesquisas;
    private Integer numPublicacoes;
    private Integer numConvites;
    private Integer numCurtidas;

    public MedalhaModelo() {
    }

    public MedalhaModelo(Integer idMedalha, LocalDate dataInicio, boolean todasInfos, Integer numPesquisas, Integer numPublicacoes, Integer numConvites, Integer numCurtidas) {
        this.idMedalha = idMedalha;
        this.dataInicio = dataInicio;
        this.todasInfos = todasInfos;
        this.numPesquisas = numPesquisas;
        this.numPublicacoes = numPublicacoes;
        this.numConvites = numConvites;
        this.numCurtidas = numCurtidas;
    }

    public Integer getIdMedalha() {
        return idMedalha;
    }

    public void setIdMedalha(Integer idMedalha) {
        this.idMedalha = idMedalha;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public boolean isTodasInfos() {
        return todasInfos;
    }

    public void setTodasInfos(boolean todasInfos) {
        this.todasInfos = todasInfos;
    }

    public Integer getNumPesquisas() {
        return numPesquisas;
    }

    public void setNumPesquisas(Integer numPesquisas) {
        this.numPesquisas = numPesquisas;
    }

    public Integer getNumPublicacoes() {
        return numPublicacoes;
    }

    public void setNumPublicacoes(Integer numPublicacoes) {
        this.numPublicacoes = numPublicacoes;
    }

    public Integer getNumConvites() {
        return numConvites;
    }

    public void setNumConvites(Integer numConvites) {
        this.numConvites = numConvites;
    }

    public Integer getNumCurtidas() {
        return numCurtidas;
    }

    public void setNumCurtidas(Integer numCurtidas) {
        this.numCurtidas = numCurtidas;
    }
}
