package br.com.musicall.api.dto;

import br.com.musicall.api.dominios.RegistroMedalha;

public class RegistroMedalhaDto extends Dto{

    private Integer idRegistroMedalha;
    private Integer regDataInicio;
    private Boolean regTodasInfos;
    private Integer regNumPesquisas;
    private Integer regNumPublicacoes;
    private Integer regNumConvites;
    private Integer regNumCurtidas;

    public RegistroMedalhaDto(RegistroMedalha registroMedalha) {
        this.idRegistroMedalha = registroMedalha.getIdRegistroMedalha();
        this.regDataInicio = registroMedalha.getRegDataInicio();
        this.regTodasInfos = registroMedalha.getRegTodasInfos();
        this.regNumPesquisas = registroMedalha.getRegNumPesquisas();
        this.regNumPublicacoes = registroMedalha.getRegNumPublicacoes();
        this.regNumConvites = registroMedalha.getRegNumConvites();
        this.regNumCurtidas = registroMedalha.getRegNumCurtidas();
    }

    public Integer getIdRegistroMedalha() {
        return idRegistroMedalha;
    }

    public Integer getRegDataInicio() {
        return regDataInicio;
    }

    public Boolean getRegTodasInfos() {
        return regTodasInfos;
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

    public Integer getRegNumCurtidas() {
        return regNumCurtidas;
    }
}
