package br.com.musicall.repositorios;

import br.com.musicall.dominios.Grupo;
import br.com.musicall.dominios.Participa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ParticipaRepository extends JpaRepository<Participa, Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into participa (grupo_id_grupo,usuario_id_usuario) values (?1,?2);", nativeQuery = true)
    void criaAssociacaoUsuarioGrupo(Integer idGrupo, Integer idUsuario);

}
