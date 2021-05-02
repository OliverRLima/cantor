package br.com.musicall.api.controllers.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class InstrumentoForm {

    @NotNull @NotEmpty
    private String instrumento;

    public void setInstrumento(String instrumento) {
        this.instrumento = instrumento;
    }

    public String getInstrumento() {
        return instrumento;
    }
}
