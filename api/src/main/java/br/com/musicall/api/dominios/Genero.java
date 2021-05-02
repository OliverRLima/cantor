package br.com.musicall.api.dominios;

import br.com.musicall.api.controllers.form.GeneroForm;

import javax.persistence.*;

@Entity
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGenero;

    @Column(nullable = false)
    private String  preferencia;

    @Column(nullable = false)
    private String generoMusical;

    @ManyToOne
    private Usuario usuario;

    public Genero() {
    }

    public Genero(Integer idGenero, String preferencia, String generoMusical, Usuario usuario) {
        this.idGenero = idGenero;
        this.preferencia = preferencia;
        this.generoMusical = generoMusical;
        this.usuario = usuario;
    }

    public Genero(GeneroForm form, Usuario usuario) {
        this.preferencia = "alta";
        this.generoMusical = form.getGeneroMusical();
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
