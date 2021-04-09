package br.com.musicall.modelos;

public class RegMedalhaModelo {

    private Integer idRegistroMedalha;
    private Integer regDataInicio;
    private Boolean regTodasInfos;
    private Integer regNumPesquisas;
    private Integer regNumPublicacoes;
    private Integer regNumConvites;
    private Integer regNumCurtidas;

    public RegMedalhaModelo() {
    }

    public RegMedalhaModelo(Integer idRegistroMedalha, Integer regDataInicio, Boolean regTodasInfos, Integer regNumPesquisas, Integer regNumPublicacoes, Integer regNumConvites, Integer regNumCurtidas) {
        this.idRegistroMedalha = idRegistroMedalha;
        this.regDataInicio = regDataInicio;
        this.regTodasInfos = regTodasInfos;
        this.regNumPesquisas = regNumPesquisas;
        this.regNumPublicacoes = regNumPublicacoes;
        this.regNumConvites = regNumConvites;
        this.regNumCurtidas = regNumCurtidas;
    }

    public Integer getIdRegistroMedalha() {
        return idRegistroMedalha;
    }

    public void setIdRegistroMedalha(Integer idRegistroMedalha) {
        this.idRegistroMedalha = idRegistroMedalha;
    }

    public Integer getRegDataInicio() {
        return regDataInicio;
    }

    public void setRegDataInicio(Integer regDataInicio) {
        this.regDataInicio = regDataInicio;
    }

    public Boolean getRegTodasInfos() {
        return regTodasInfos;
    }

    public void setRegTodasInfos(Boolean regTodasInfos) {
        this.regTodasInfos = regTodasInfos;
    }

    public Integer getRegNumPesquisas() {
        return regNumPesquisas;
    }

    public void setRegNumPesquisas(Integer regNumPesquisas) {
        this.regNumPesquisas = regNumPesquisas;
    }

    public Integer getRegNumPublicacoes() {
        return regNumPublicacoes;
    }

    public void setRegNumPublicacoes(Integer regNumPublicacoes) {
        this.regNumPublicacoes = regNumPublicacoes;
    }

    public Integer getRegNumConvites() {
        return regNumConvites;
    }

    public void setRegNumConvites(Integer regNumConvites) {
        this.regNumConvites = regNumConvites;
    }

    public Integer getRegNumCurtidas() {
        return regNumCurtidas;
    }

    public void setRegNumCurtidas(Integer regNumCurtidas) {
        this.regNumCurtidas = regNumCurtidas;
    }
}
