package br.com.musicall.api.dto;

import br.com.musicall.api.dominios.Genero;

public class GeneroDto {

    private Integer idGenero;
    private String generoMusical;

    public GeneroDto(Genero genero) {
        this.idGenero = genero.getIdGenero();
        this.generoMusical = genero.getGeneroMusical();
    }

    public Integer getIdGenero() {
        return idGenero;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }
}
