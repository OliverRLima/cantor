package br.com.musicall.api.aplicacao.excecoes;

public class UsuarioInexistenteException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public UsuarioInexistenteException(String mensagem) {
        super(mensagem);
    }
}
