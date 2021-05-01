package br.com.musicall.api.dto;

import br.com.musicall.api.dominios.Instrumento;

public class InstrumentoDto extends Dto{

    private Integer idInstrumento;
    private String tipoInstrumento;
    private String instrumento;
    private String  nvDominio;

    public InstrumentoDto(Instrumento inst) {
        this.idInstrumento = inst.getIdInstrumento();
        this.tipoInstrumento = inst.getTipoInstrumento();
        this.instrumento = inst.getInstrumento();
        this.nvDominio = inst.getNvDominio();
    }

    public Integer getIdInstrumento() {
        return idInstrumento;
    }

    public String getTipoInstrumento() {
        return tipoInstrumento;
    }

    public String getInstrumento() {
        return instrumento;
    }

    public String getNvDominio() {
        return nvDominio;
    }
}
