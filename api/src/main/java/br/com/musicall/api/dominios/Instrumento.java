
package br.com.musicall.api.dominios;

import br.com.musicall.api.controllers.form.InstrumentoForm;

import javax.persistence.*;

@Entity
public class Instrumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInstrumento;

    @Column(nullable = false)
    private String tipoInstrumento;

    @Column(nullable = false)
    private String instrumento;

    @Column(nullable = false)
    private String  nvDominio;

    @ManyToOne
    private Usuario usuario;

    public Instrumento() {
    }

    public Instrumento(Integer idInstrumento, String tipoInstrumento, String instrumento, String nvDominio, Usuario usuario) {
        this.idInstrumento = idInstrumento;
        this.tipoInstrumento = tipoInstrumento;
        this.instrumento = instrumento;
        this.nvDominio = nvDominio;
        this.usuario = usuario;
    }

    public Instrumento(InstrumentoForm form, Usuario usuario) {
        this.tipoInstrumento = "corda";
        this.instrumento = form.getInstrumento();
        this.nvDominio = "medio";
        this.usuario = usuario;
    }

    public Integer getIdInstrumento() {
        return idInstrumento;
    }

    public void setIdInstrumento(Integer idInstrumento) {
        this.idInstrumento = idInstrumento;
    }

    public String getTipoInstrumento() {
        return tipoInstrumento;
    }

    public void setTipoInstrumento(String tipoInstrumento) {
        this.tipoInstrumento = tipoInstrumento;
    }

    public String getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(String instrumento) {
        this.instrumento = instrumento;
    }

    public String getNvDominio() {
        return nvDominio;
    }

    public void setNvDominio(String nvDominio) {
        this.nvDominio = nvDominio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
