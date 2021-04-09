package br.com.musicall.modelos;

import java.time.LocalDate;

public class PublicacaoModelo {

    private Integer idPublicacao;
    private LocalDate dataPublicacao;
    private Integer reporte;
    private Integer curtida;
    private String texto;

    public PublicacaoModelo() {
    }

    public PublicacaoModelo(Integer idPublicacao, LocalDate dataPublicacao, Integer reporte, Integer curtida, String texto) {
        this.idPublicacao = idPublicacao;
        this.dataPublicacao = dataPublicacao;
        this.reporte = reporte;
        this.curtida = curtida;
        this.texto = texto;
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

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
