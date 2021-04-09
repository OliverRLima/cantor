package br.com.musicall.repositorios;

import br.com.musicall.dominios.Publicacao;
import br.com.musicall.dominios.Recebe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface RecebeRepository extends JpaRepository<Recebe, Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into recebe (publicacao_id_publicacao,usuario_id_usuario) values (?1,?2);", nativeQuery = true)
    void notificaUsuarios(Integer primeiroId, Integer segundoId);

    @Query("select p from Publicacao p, Recebe r, Usuario u where r.usuario.idUsuario = ?1 and r.publicacao.idPublicacao = p.idPublicacao")
    List<Publicacao> buscarPublicacaoPorId(Integer idUsuario);
}
