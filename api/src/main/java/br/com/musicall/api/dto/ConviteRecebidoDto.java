package br.com.musicall.api.dto;

import br.com.musicall.api.dominios.Convite;
import br.com.musicall.api.dominios.Genero;
import br.com.musicall.api.dominios.Instrumento;
import br.com.musicall.api.dominios.Usuario;

import java.time.LocalDate;
import java.time.Period;

public class ConviteRecebidoDto {

    private Integer idConvite;
    private String nome;
    private String instrumento;
    private String anos;
    private String estado;
    private String genero;
    private Boolean aceito;

    public ConviteRecebidoDto(Convite convite, Usuario usuario, Instrumento instrumento, Genero genero) {
        this.idConvite = convite.getIdConvite();
        this.nome = usuario.getNome();
        this.instrumento = instrumento.getInstrumento();
        this.anos = String.format("%d anos", getAnosIdade(usuario));;
        this.estado = usuario.getInfoUsuario().getEstado();
        this.genero = genero.getGeneroMusical();
        this.aceito = convite.isConfirmado();
    }

    private Integer getAnosIdade(Usuario usuario) {
        Period period = Period.between(usuario.getInfoUsuario().getDataAniversario(), LocalDate.now());
        Integer anos = period.getYears();
        return anos;
    }

    public Integer getIdConvite() {
        return idConvite;
    }

    public String getNome() {
        return nome;
    }

    public String getInstrumento() {
        return instrumento;
    }

    public String getAnos() {
        return anos;
    }

    public String getEstado() {
        return estado;
    }

    public String getGenero() {
        return genero;
    }

    public Boolean getAceito() {
        return aceito;
    }
}
