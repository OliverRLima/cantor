package br.com.musicall.classes;

import br.com.musicall.dominios.Publicacao;

import java.util.ArrayList;
import java.util.List;

public interface Observer {
    
    List<Publicacao> update(Integer idUsuario);

}
