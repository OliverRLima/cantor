package br.com.musicall.api.dto;

import br.com.musicall.api.dominios.Genero;

public class GeneroDto extends Dto{

    private Integer idGenero;
    private String  preferencia;
    private String generoMusical;

    public GeneroDto(Genero genero) {
        this.idGenero = genero.getIdGenero();
        this.preferencia = genero.getPreferencia();
        this.generoMusical = genero.getGeneroMusical();
    }

    public Integer getIdGenero() {
        return idGenero;
    }

    public String getPreferencia() {
        return preferencia;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }
}
