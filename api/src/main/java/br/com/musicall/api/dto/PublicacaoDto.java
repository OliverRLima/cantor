package br.com.musicall.api.dto;

import br.com.musicall.api.dominios.Genero;
import br.com.musicall.api.dominios.Instrumento;
import br.com.musicall.api.dominios.Publicacao;

import java.time.LocalDate;
import java.time.Period;

public class PublicacaoDto {

    private Integer idUsuario;
    private String nome;
    private String instrumento;
    private String idade;
    private String estado;
    private String genero;
    private String texto;

    public PublicacaoDto(Publicacao publicacao, Instrumento instrumento, Genero genero){
        this.idUsuario = publicacao.getUsuario().getIdUsuario();
        this.nome = publicacao.getUsuario().getNome();
        this.instrumento = instrumento.getInstrumento();
        this.idade = String.format("%d anos", getAnosIdade(publicacao));
        this.estado = publicacao.getUsuario().getInfoUsuario().getEstado();
        this.genero = genero.getGeneroMusical();
        this.texto = publicacao.getTexto();
    }


    private Integer getAnosIdade(Publicacao publicacao) {
        Period period = Period.between(publicacao.getUsuario().getInfoUsuario().getDataAniversario(), LocalDate.now());
        Integer anos = period.getYears();
        return anos;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public String getInstrumento() {
        return instrumento;
    }

    public String getIdade() {
        return idade;
    }

    public String getEstado() {
        return estado;
    }

    public String getGenero() {
        return genero;
    }

    public String getTexto() {
        return texto;
    }
}
