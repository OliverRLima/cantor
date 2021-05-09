package br.com.musicall.api.repositorios;

import br.com.musicall.api.dominios.Publicacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Integer> {

    List<Publicacao> findAllByUsuarioIdUsuario(Integer idUsuario);

    @Transactional
    @Modifying
    @Query(value = "insert into publicacao (data_publicacao, reporte, texto, usuario_id_usuario, curtida) values (?1,0,?2,?3,0);", nativeQuery = true)
    void publicar(LocalDate dataPublicacao, String texto, Integer idUsuario);

    @Transactional
    void deleteByUsuarioIdUsuario(Integer idUsuario);
}
