package br.com.musicall.classes;

import br.com.musicall.dominios.Publicacao;
import br.com.musicall.dominios.Usuario;

import java.util.ArrayList;
import java.util.List;

public interface Subject {

    void notifica(Integer idPublicacao, List<Usuario> usuarios);
}
