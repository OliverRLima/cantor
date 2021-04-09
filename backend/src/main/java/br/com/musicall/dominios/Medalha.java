package br.com.musicall.dominios;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Medalha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMedalha;

    @Column(nullable = false)
    private LocalDate dataInicio;

    @Column(nullable = false)
    private Boolean todasInfos;

    @Column(nullable = false)
    private Integer numPesquisas;

    @Column(nullable = false)
    private Integer numPublicacoes;

    @Column(nullable = false)
    private Integer numConvites;

    @Column(nullable = false)
    private Integer numCurtidas;

    @ManyToOne
    private Usuario usuario;

    public Medalha() {
    }

    public Medalha(Integer idMedalha, LocalDate dataInicio, Boolean todasInfos, Integer numPesquisas, Integer numPublicacoes, Integer numConvites, Integer numCurtidas, Usuario usuario) {
        this.idMedalha = idMedalha;
        this.dataInicio = dataInicio;
        this.todasInfos = todasInfos;
        this.numPesquisas = numPesquisas;
        this.numPublicacoes = numPublicacoes;
        this.numConvites = numConvites;
        this.numCurtidas = numCurtidas;
        this.usuario = usuario;
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

    public Boolean getTodasInfos() {
        return todasInfos;
    }

    public void setTodasInfos(Boolean todasInfos) {
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
