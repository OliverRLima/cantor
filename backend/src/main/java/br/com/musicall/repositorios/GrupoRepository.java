package br.com.musicall.repositorios;

import br.com.musicall.dominios.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GrupoRepository extends JpaRepository<Grupo, Integer> {

    @Query("select g from Grupo g where g.idCriador = ?1 and g.descricao = ?1")
    Grupo getGrupoByIdCriador(Integer idCriador, String descricao);

    @Query("select g from Grupo g where g.idGrupo = ?1")
    Grupo getGrupoByIdGrupo(Integer idGrupo);

    @Query("select g from Grupo g where g.idCriador = ?1")
    List<Grupo> getGruposByIdCriador(Integer idCriador);

    @Query("SELECT g FROM Participa p JOIN p.grupo g where p.usuario.idUsuario = ?1")
    List<Grupo> getGruposAssociadosPeloIdUsuario(Integer idUsuario);

}
