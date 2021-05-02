package br.com.musicall.api.dto;

import br.com.musicall.api.dominios.Instrumento;

public class InstrumentoDto {

    private Integer idInstrumento;
    private String instrumento;

    public InstrumentoDto(Instrumento instrumento) {
        this.idInstrumento = instrumento.getIdInstrumento();
        this.instrumento = instrumento.getInstrumento();
    }

    public Integer getIdInstrumento() {
        return idInstrumento;
    }

    public String getInstrumento() {
        return instrumento;
    }
}
