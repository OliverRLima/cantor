package br.com.musicall.modelos;

public class InstrumentoModelo {

    private Integer idInstrumento;
    private String tipoInstrumento;
    private String instrumento;
    private String nvDominio;

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

}
