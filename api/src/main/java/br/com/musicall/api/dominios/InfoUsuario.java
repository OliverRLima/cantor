package br.com.musicall.api.dominios;

import br.com.musicall.api.controllers.form.InfoForm;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class InfoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInfoUsuario;

    @Column(nullable = false)
    private LocalDate dataAniversario;

    @Column(nullable = false, length = 255)
    private String descricao;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String cidade;

    public InfoUsuario() {
    }

    public InfoUsuario(Integer idInfoUsuario, LocalDate dataAniversario, String descricao, String estado, String cidade) {
        this.idInfoUsuario = idInfoUsuario;
        this.dataAniversario = dataAniversario;
        this.descricao = descricao;
        this.estado = estado;
        this.cidade = cidade;
    }

    public InfoUsuario(InfoForm form) {
        this.dataAniversario = form.getDataAniversario();
        this.descricao = "Padrao";
        this.estado = form.getEstado();
        this.cidade = form.getCidade();
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

    @Override
    public String toString() {
        return "InfoUsuario{" +
                "idInfoUsuario=" + idInfoUsuario +
                ", dataAniversario=" + dataAniversario +
                ", descricao='" + descricao + '\'' +
                ", estado='" + estado + '\'' +
                ", cidade='" + cidade + '\'' +
                '}';
    }
}
