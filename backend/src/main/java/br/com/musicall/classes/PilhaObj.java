package br.com.musicall.classes;

public class PilhaObj<Generic> {
    private int topo;
    private Generic[] vetor;

    public PilhaObj(int tamanho) {
        vetor = (Generic[]) new Object[tamanho];
        topo = -1;
    }

    public boolean isEmpty() {
        if (topo == -1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFull() {
        if (topo == (vetor.length - 1)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean push(Generic item) {
        if (!isFull()) {
            vetor[++topo] = item;
            return true;
        } else {
            return false;
        }
    }

    public Generic pop() {
        if (!isEmpty()) {
            return vetor[topo--];
        }
        return null;
    }

    public Generic peek() {
        if (!isEmpty()) {
            return vetor[topo];
        }
        return null;
    }

    public Generic get(int item) {
        if (!isEmpty()) {
            return vetor[item];
        }
        return null;
    }

    public void exibe() {
        if (!isEmpty()) {
            for (int cc = 0; cc <= topo; cc++) {
                vetor[cc].toString();
            }
        } else {
            System.out.println("Vazio");
        }
    }

}
