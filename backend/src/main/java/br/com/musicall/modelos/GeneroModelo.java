package br.com.musicall.modelos;

public class GeneroModelo {

    private Integer idGenero;
    private String preferencia;
    private String generoMusical;

    public GeneroModelo() {
    }

    public GeneroModelo(Integer idGenero, String preferencia, String generoMusical) {
        this.idGenero = idGenero;
        this.preferencia = preferencia;
        this.generoMusical = generoMusical;
    }

    public Integer getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public String getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(String preferencia) {
        this.preferencia = preferencia;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }
}
