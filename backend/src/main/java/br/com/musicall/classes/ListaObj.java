package br.com.musicall.classes;

public class ListaObj<T> {

    private T[] vetor;
    private int nroElem;

    public ListaObj(int tamanho) {
        this.vetor = (T[]) new Object[tamanho];
        this.nroElem = 0;
    }

    public boolean adiciona(T elemento) {
        if (this.nroElem >= this.vetor.length) {
            System.out.println("Lista está cheia");
            return false;
        } else {
            this.vetor[this.nroElem++] = (T) elemento;
            return true;
        }
    }

    public void exibe() {
        for(int i = 0; i < this.nroElem; ++i) {
            System.out.println(this.vetor[i]);
        }

    }

    public int busca(T elemento) {
        int procura = -1;

        for(int i = 0; i < this.nroElem; ++i) {
            if (elemento.equals(this.vetor[i])) {
                procura = i;
            }
        }

        return procura;
    }

    public boolean removePeloIndice(int posicao) {
        if (posicao < this.nroElem && posicao >= 0) {
            for(int i = posicao; i + 1 < this.nroElem; ++i) {
                this.vetor[i] = this.vetor[i + 1];
            }

            if (this.nroElem > 0) {
                --this.nroElem;
            }

            return true;
        } else {
            return false;
        }
    }

    public boolean removeElemento(T elemento) {
        int procura = this.busca(elemento);
        if (procura == -1) {
            return false;
        } else {
            this.removePeloIndice(procura);
            return true;
        }
    }

    public T getElemento(int posicao) {
        if (posicao < 0 || posicao >= nroElem) {  // se índice for inválido
            return null;                       // retorna false
        }
        else {
            return vetor[posicao];
        }
    }

    public void limpa() {
        this.nroElem = 0;
    }

    public int getTamanho() {
        return this.nroElem;
    }

}
