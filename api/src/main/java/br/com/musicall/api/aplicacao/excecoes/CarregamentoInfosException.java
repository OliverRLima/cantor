package br.com.musicall.api.aplicacao.excecoes;

public class CarregamentoInfosException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CarregamentoInfosException(String mensagem) {
        super(mensagem);
    }
}
