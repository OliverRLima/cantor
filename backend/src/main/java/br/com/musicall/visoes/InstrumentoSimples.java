package br.com.musicall.visoes;

public class InstrumentoSimples {

    private Integer idInstrumento;
    private String tipoInstrumento;
    private String instrumento;
    private String nvDominio;

    public InstrumentoSimples() {
    }

    public InstrumentoSimples(Integer idInstrumento, String tipoInstrumento, String instrumento, String nvDominio) {
        this.idInstrumento = idInstrumento;
        this.tipoInstrumento = tipoInstrumento;
        this.instrumento = instrumento;
        this.nvDominio = nvDominio;
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
}
