package br.com.musicall.repositorios;

import br.com.musicall.dominios.Medalha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDate;

public interface MedalhasRepository extends JpaRepository<Medalha, Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into medalha (data_inicio, todas_infos, num_pesquisas, num_publicacoes, num_convites, num_curtidas, usuario_id_usuario) " +
            "values (?1,0,0,0,0,0,?2);", nativeQuery = true)
    void adicionarMedalha(LocalDate dataInicio, Integer idUsuario);

    @Transactional
    @Modifying
    @Query("update Medalha m set m.todasInfos = 1 where m.usuario.idUsuario = ?1")
    void alterarTodasInfos(Integer idUsuario);

    @Transactional
    @Modifying
    @Query("update Medalha m set m.numConvites = ?1 where m.usuario.idUsuario = ?2")
    void alterarNumConvites(Integer numConvites, Integer idUsuario);

    @Transactional
    @Modifying
    @Query("update Medalha m set m.numCurtidas = ?1 where m.usuario.idUsuario = ?2")
    void alterarNumCurtidas(Integer numCurtidas, Integer idUsuario);

    @Transactional
    @Modifying
    @Query("update Medalha m set m.numPesquisas = ?1 where m.usuario.idUsuario = ?2")
    void alterarNumPesquisas(Integer numPesquisas, Integer idUsuario);

    @Transactional
    @Modifying
    @Query("update Medalha m set m.numPublicacoes = ?1 where m.usuario.idUsuario = ?2")
    void alterarNumPublicacoes(Integer numPublicacoes, Integer idUsuario);

    @Query("select m from Medalha m where m.usuario.idUsuario = ?1")
    Medalha getPorIdUsuario(Integer idUsuario);

}
