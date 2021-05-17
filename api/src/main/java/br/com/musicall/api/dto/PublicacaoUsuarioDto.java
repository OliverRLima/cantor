package br.com.musicall.api.dto;

import br.com.musicall.api.dominios.Genero;
import br.com.musicall.api.dominios.Instrumento;
import br.com.musicall.api.dominios.Publicacao;

import java.time.LocalDate;
import java.time.Period;

public class PublicacaoUsuarioDto {

    private Integer idPublicacao;
    private String nome;
    private String texto;

    public PublicacaoUsuarioDto(Publicacao publicacao){
        this.idPublicacao = publicacao.getIdPublicacao();
        this.nome = publicacao.getUsuario().getNome();
        this.texto = publicacao.getTexto();
    }

    public Integer getIdPublicacao() {
        return idPublicacao;
    }

    public String getNome() {
        return nome;
    }

    public String getTexto() {
        return texto;
    }
}
