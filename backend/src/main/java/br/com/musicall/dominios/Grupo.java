package br.com.musicall.dominios;

import javax.persistence.*;

@Entity
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGrupo;

    @Column(nullable = false)
    private Integer idCriador;

    private boolean visibilidade;

    @Column(nullable = false, length = 255)
    private String descricao;

    public Grupo() {
    }

    public Grupo(Integer idGrupo, Integer idCriador, boolean visibilidade, String descricao) {
        this.idGrupo = idGrupo;
        this.idCriador = idCriador;
        this.visibilidade = visibilidade;
        this.descricao = descricao;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getIdCriador() {
        return idCriador;
    }

    public void setIdCriador(Integer idCriador) {
        this.idCriador = idCriador;
    }

    public boolean getVisibilidade() {
        return visibilidade;
    }

    public void setVisibilidade(boolean visibilidade) {
        this.visibilidade = visibilidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
