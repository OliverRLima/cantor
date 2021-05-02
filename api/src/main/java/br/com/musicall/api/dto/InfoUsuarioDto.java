package br.com.musicall.api.dto;

import br.com.musicall.api.dominios.InfoUsuario;

import java.time.LocalDate;

public class InfoUsuarioDto {

    private Integer idInfoUsuario;
    private LocalDate dataAniversario;
    private String estado;
    private String cidade;

    public InfoUsuarioDto(InfoUsuario infoUsuario) {
        this.idInfoUsuario = infoUsuario.getIdInfoUsuario();
        this.dataAniversario = infoUsuario.getDataAniversario();
        this.estado = infoUsuario.getEstado();
        this.cidade = infoUsuario.getCidade();
    }

    public Integer getIdInfoUsuario() {
        return idInfoUsuario;
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
}
