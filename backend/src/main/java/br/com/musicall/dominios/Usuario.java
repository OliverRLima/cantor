package br.com.musicall.dominios;

import javax.persistence.*;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 50)
    private String senha;

    @Column(nullable = true)
    private boolean notificado;

    @ManyToOne
    private RedeSocial redeSocial;

    @ManyToOne
    private InfoUsuario infoUsuario;

    public Usuario() {
    }

    public Usuario(Integer idUsuario, String nome, String email, String senha, boolean notificado, RedeSocial redeSocial, InfoUsuario infoUsuario) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.notificado = notificado;
        this.redeSocial = redeSocial;
        this.infoUsuario = infoUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isNotificado() {
        return notificado;
    }

    public void setNotificado(boolean notificado) {
        this.notificado = notificado;
    }

    public RedeSocial getRedeSocial() {
        return redeSocial;
    }

    public void setRedeSocial(RedeSocial redeSocial) {
        this.redeSocial = redeSocial;
    }

    public InfoUsuario getInfoUsuario() {
        return infoUsuario;
    }

    public void setInfoUsuario(InfoUsuario infoUsuario) {
        this.infoUsuario = infoUsuario;
    }

}
