package br.com.musicall.api.dto;

import br.com.musicall.api.dominios.RegistroMedalha;

public class RegistroMedalhaDto {

    private Integer regDataInicio;
    private Boolean regTodasinfos;
    private Integer regNumPesquisas;
    private Integer regNumPublicacoes;
    private Integer regNumConvites;

    public RegistroMedalhaDto(RegistroMedalha registroMedalha) {
        this.regDataInicio = registroMedalha.getRegDataInicio();
        this.regTodasinfos = registroMedalha.getRegTodasInfos();
        this.regNumPesquisas = registroMedalha.getRegNumPesquisas();
        this.regNumPublicacoes = registroMedalha.getRegNumPublicacoes();
        this.regNumConvites = registroMedalha.getRegNumConvites();
    }

    public Integer getRegDataInicio() {
        return regDataInicio;
    }

    public Boolean getRegTodasinfos() {
        return regTodasinfos;
    }

    public Integer getRegNumPesquisas() {
        return regNumPesquisas;
    }

    public Integer getRegNumPublicacoes() {
        return regNumPublicacoes;
    }

    public Integer getRegNumConvites() {
        return regNumConvites;
    }
}
