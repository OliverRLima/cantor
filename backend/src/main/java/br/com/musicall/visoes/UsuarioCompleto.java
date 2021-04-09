package br.com.musicall.visoes;

public class UsuarioCompleto {

    private Integer idUsuario;
    private String nome;
    private String instrumento;
    private String genero;
    private String localizacao;
    private String idade;
    private String facebook;
    private String instagram;
    private String telefone;

    public UsuarioCompleto() {
    }

    public UsuarioCompleto(Integer idUsuario, String nome, String instrumento, String genero, String localizacao, String idade, String facebook, String instagram, String telefone) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.instrumento = instrumento;
        this.genero = genero;
        this.localizacao = localizacao;
        this.idade = idade;
        this.facebook = facebook;
        this.instagram = instagram;
        this.telefone = telefone;
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

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
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
}
