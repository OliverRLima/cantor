package br.com.musicall.dominios;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Publicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPublicacao;

    private LocalDate dataPublicacao;

    @Column(nullable = false,length = 255)
    private String texto;

    @Column(nullable = false)
    private Integer reporte;

    @Column(nullable = false)
    private Integer curtida;

    @ManyToOne
    private Usuario usuario;

    public Publicacao() {
    }

    public Publicacao(Integer idPublicacao, LocalDate dataPublicacao, String texto, Integer reporte, Integer curtida, Usuario usuario) {
        this.idPublicacao = idPublicacao;
        this.dataPublicacao = dataPublicacao;
        this.texto = texto;
        this.reporte = reporte;
        this.curtida = curtida;
        this.usuario = usuario;
    }

    public Integer getIdPublicacao() {
        return idPublicacao;
    }

    public void setIdPublicacao(Integer idPublicacao) {
        this.idPublicacao = idPublicacao;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Integer getReporte() {
        return reporte;
    }

    public void setReporte(Integer reporte) {
        this.reporte = reporte;
    }

    public Integer getCurtida() {
        return curtida;
    }

    public void setCurtida(Integer curtida) {
        this.curtida = curtida;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
