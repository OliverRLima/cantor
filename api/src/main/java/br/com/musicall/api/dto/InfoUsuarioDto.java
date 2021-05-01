package br.com.musicall.api.dto;

import br.com.musicall.api.dominios.InfoUsuario;

import java.time.LocalDate;

public class InfoUsuarioDto extends Dto{

    private Integer idInfoUsuario;
    private LocalDate dataAniversario;
    private String descricao;
    private String estado;
    private String cidade;

    public InfoUsuarioDto(InfoUsuario infoUsuario) {
        this.idInfoUsuario = infoUsuario.getIdInfoUsuario();
        this.dataAniversario = infoUsuario.getDataAniversario();
        this.descricao = infoUsuario.getDescricao();
        this.estado = infoUsuario.getEstado();
        this.cidade = infoUsuario.getCidade();
    }

    public Integer getIdInfoUsuario() {
        return idInfoUsuario;
    }

    public LocalDate getDataAniversario() {
        return dataAniversario;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }
}
