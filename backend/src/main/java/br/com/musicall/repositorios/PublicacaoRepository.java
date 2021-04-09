package br.com.musicall.repositorios;

import br.com.musicall.dominios.Publicacao;
import br.com.musicall.visoes.PublicacaoSimples;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Integer> {

    @Query("select new br.com.musicall.visoes.PublicacaoSimples(u.nome, u.email, p.texto, p.dataPublicacao) from Publicacao p, Usuario u where u.idUsuario = p.usuario.idUsuario")
    List<PublicacaoSimples> procurarTodosSimples();

    @Query("select p from Publicacao p where p.usuario.idUsuario = ?1")
    List<Publicacao> getTodasPorIdUsuario(Integer idUsuario);

    @Query("select p from Publicacao p where p.usuario.nome = ?1")
    List<Publicacao> getTodasPorUsuarioNome(String nome);

    @Query("select p from Publicacao p where p.usuario.infoUsuario.cidade = ?1")
    List<Publicacao> getTodasPorCidade(String cidade);

    @Query("select p from Publicacao p where p.idPublicacao = ?1")
    Publicacao getPorIdPublicacao(Integer idPublicacao);

    @Query("select p from Publicacao p where p.dataPublicacao = ?1 and p.texto = ?2 and p.usuario.idUsuario = ?3")
    Publicacao getPorIdusuarioTextoData(LocalDate dataPublicacao, String texto, Integer idUsuario);

    @Transactional
    @Modifying
    @Query(value = "insert into publicacao (data_publicacao, reporte, texto, usuario_id_usuario, curtida) values (?1,?2,?3,?4,0);", nativeQuery = true)
    void publicar(LocalDate dataPublicacao, Integer reporte, String texto, Integer idUsuario);

    @Transactional
    @Modifying
    @Query("delete from Publicacao p where p.usuario.idUsuario = ?1")
    void deletarPeloIdUsuario(Integer idUsuario);

    @Transactional
    @Modifying
    @Query("update Publicacao p set p.texto = ?1 where p.idPublicacao = ?2")
    void alterarTexto(String texto, Integer idPublicacao);

    @Transactional
    @Modifying
    @Query("update Publicacao p set p.curtida = ?1 where p.idPublicacao = ?2")
    void alterarCurtidas(Integer curtida, Integer idPublicacao);
}
