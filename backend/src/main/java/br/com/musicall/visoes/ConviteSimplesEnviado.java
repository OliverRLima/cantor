package br.com.musicall.visoes;

public class ConviteSimplesEnviado {

    private Integer idConvite;

    private String nome;

    private String instrumento;

    private String genero;

    private String facebook;

    private String instagram;

    private String telefone;

    private String estado;

    private String cidade;

    private boolean visualizar;

    public ConviteSimplesEnviado() {
    }

    public ConviteSimplesEnviado(Integer idConvite, String nome, String instrumento, String genero, String facebook, String instagram, String telefone, String estado, String cidade, boolean visualizar) {
        this.idConvite = idConvite;
        this.nome = nome;
        this.instrumento = instrumento;
        this.genero = genero;
        this.facebook = facebook;
        this.instagram = instagram;
        this.telefone = telefone;
        this.estado = estado;
        this.cidade = cidade;
        this.visualizar = visualizar;
    }

    public Integer getIdConvite() {
        return idConvite;
    }

    public void setIdConvite(Integer idConvite) {
        this.idConvite = idConvite;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(String instrumento) {
        this.instrumento = instrumento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public boolean isVisualizar() {
        return visualizar;
    }

    public void setVisualizar(boolean visualizar) {
        this.visualizar = visualizar;
    }
}
