package br.com.musicall.dominios;

import javax.persistence.*;

@Entity
public class Recebe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRecebe;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Publicacao publicacao;

    public Recebe() {
    }

    public Recebe(Integer idRecebe, Usuario usuario, Publicacao publicacao) {
        this.idRecebe = idRecebe;
        this.usuario = usuario;
        this.publicacao = publicacao;
    }

    public Integer getIdRecebe() {
        return idRecebe;
    }

    public void setIdRecebe(Integer idRecebe) {
        this.idRecebe = idRecebe;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }
}
