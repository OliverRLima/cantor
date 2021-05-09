package br.com.musicall.api.dominios;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Convite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConvite;

    @Column(nullable = false)
    private LocalDate dataConvite;

    @Column(nullable = false)
    private Integer idConvidou;

    @Column(nullable = false)
    private Integer idConvidado;

    @Column(nullable = false)
    private boolean confirmado;

    public Convite() {
    }

    public Convite(Integer idConvite, LocalDate dataConvite, Integer idConvidou, Integer idConvidado, boolean confirmado) {
        this.idConvite = idConvite;
        this.dataConvite = dataConvite;
        this.idConvidou = idConvidou;
        this.idConvidado = idConvidado;
        this.confirmado = confirmado;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }

    public Integer getIdConvite() {
        return idConvite;
    }

    public void setIdConvite(Integer idConvite) {
        this.idConvite = idConvite;
    }

    public LocalDate getDataConvite() {
        return dataConvite;
    }

    public void setDataConvite(LocalDate dataConvite) {
        this.dataConvite = dataConvite;
    }

    public Integer getIdConvidou() {
        return idConvidou;
    }

    public void setIdConvidou(Integer idConvidou) {
        this.idConvidou = idConvidou;
    }

    public Integer getIdConvidado() {
        return idConvidado;
    }

    public void setIdConvidado(Integer idConvidado) {
        this.idConvidado = idConvidado;
    }

    @Override
    public String toString() {
        return "Convite{" +
                "idConvite=" + idConvite +
                ", dataConvite=" + dataConvite +
                ", idConvidou=" + idConvidou +
                ", idConvidado=" + idConvidado +
                ", confirmado=" + confirmado +
                '}';
    }


}
