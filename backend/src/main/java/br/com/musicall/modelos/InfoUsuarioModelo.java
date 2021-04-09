package br.com.musicall.modelos;

import java.time.LocalDate;

public class InfoUsuarioModelo {

    private Integer idInfoUsuario;
    private LocalDate dataAniversario;
    private String descricao;
    private String estado;
    private String cidade;

    public InfoUsuarioModelo(Integer idInfoUsuario, LocalDate dataAniversario, String descricao, String estado, String cidade) {
        this.idInfoUsuario = idInfoUsuario;
        this.dataAniversario = dataAniversario;
        this.descricao = descricao;
        this.estado = estado;
        this.cidade = cidade;
    }

    public Integer getIdInfoUsuario() {
        return idInfoUsuario;
    }

    public void setIdInfoUsuario(Integer idInfoUsuario) {
        this.idInfoUsuario = idInfoUsuario;
    }

    public LocalDate getDataAniversario() {
        return dataAniversario;
    }

    public void setDataAniversario(LocalDate dataAniversario) {
        this.dataAniversario = dataAniversario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
