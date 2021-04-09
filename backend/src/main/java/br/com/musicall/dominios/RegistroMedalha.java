package br.com.musicall.dominios;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class RegistroMedalha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRegistroMedalha;

    @Column(nullable = false)
    private Integer regDataInicio;

    @Column(nullable = false)
    private Boolean regTodasInfos;

    @Column(nullable = false)
    private Integer regNumPesquisas;

    @Column(nullable = false)
    private Integer regNumPublicacoes;

    @Column(nullable = false)
    private Integer regNumConvites;

    @Column(nullable = false)
    private Integer regNumCurtidas;

    @ManyToOne
    private Usuario usuario;

    public RegistroMedalha() {
    }

    public RegistroMedalha(Integer idRegistroMedalha, Integer regDataInicio, Boolean regTodasInfos, Integer regNumPesquisas, Integer regNumPublicacoes, Integer regNumConvites, Integer regNumCurtidas, Usuario usuario) {
        this.idRegistroMedalha = idRegistroMedalha;
        this.regDataInicio = regDataInicio;
        this.regTodasInfos = regTodasInfos;
        this.regNumPesquisas = regNumPesquisas;
        this.regNumPublicacoes = regNumPublicacoes;
        this.regNumConvites = regNumConvites;
        this.regNumCurtidas = regNumCurtidas;
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
