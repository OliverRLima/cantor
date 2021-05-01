package br.com.musicall.api.aplicacao.excecoes;

public class UsuarioCompletoException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public UsuarioCompletoException(String mensagem) {
        super(mensagem);
    }
}
