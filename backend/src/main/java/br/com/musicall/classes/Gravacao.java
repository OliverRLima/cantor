package br.com.musicall.classes;

import br.com.musicall.dominios.Publicacao;
import br.com.musicall.visoes.PublicacaoSimples;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.FormatterClosedException;

public class Gravacao {

    public String gravaCSV(ListaObj<Publicacao> lista) {
        String retorno = "";

        for (int i = 0; i < lista.getTamanho(); i++) {
            Publicacao p = lista.getElemento(i);
            retorno = String.format("%d;%s;%s;%d%n", p.getIdPublicacao(), p.getDataPublicacao(), p.getTexto(), p.getReporte());
        }

        return retorno;
    }

    public String gravaTxt(ListaObj<PublicacaoSimples> lista) {
        String retorno = "";
        int contRegDados = 0;

        Date dataDeHoje = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        if (contRegDados == 0)
            retorno += "00PUBLICACOES";
        else
            retorno = "00PUBLICACOES";
        retorno += formatter.format(dataDeHoje);
        retorno += "01";

        for (int i = 0; i < lista.getTamanho(); i++) {

            if (contRegDados == 0)
                retorno += "\n01";
            else
                retorno = "\n01";
            retorno += String.format("%-50s", lista.getElemento(i).getNome());
            retorno += String.format("%-50s", lista.getElemento(i).getEmail());
            retorno += String.format("%-255s", lista.getElemento(i).getPublicacao());
            retorno += String.format("%10s", lista.getElemento(i).getData());
            contRegDados++;
        }

        retorno += "\n02";
        retorno += String.format("%010d", contRegDados);

        return retorno;
    }

}