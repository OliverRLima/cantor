package br.com.musicall.api.repositorios;

import br.com.musicall.api.dominios.Medalha;
import br.com.musicall.api.dominios.RegistroMedalha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface RegMedalhaRepository extends JpaRepository<RegistroMedalha, Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into registro_medalha (reg_data_inicio, reg_todas_infos, reg_num_pesquisas, reg_num_publicacoes," +
            " reg_num_convites, reg_num_curtidas, usuario_id_usuario) " +
            "values (0,0,0,0,0,0,?1);", nativeQuery = true)
    void criarRegistroMedalha(Integer idUsuario);

    @Transactional
    @Modifying
    @Query("update RegistroMedalha rm set rm.regTodasInfos = 1 where rm.usuario.idUsuario = ?1")
    void alterarTodasInfos(Integer idUsuario);

    @Transactional
    @Modifying
    @Query("update RegistroMedalha rm set rm.regNumConvites = ?1 where rm.usuario.idUsuario = ?2")
    void alterarNumConvites(Integer regNumConvites, Integer idUsuario);

    @Transactional
    @Modifying
    @Query("update RegistroMedalha rm set rm.regNumPesquisas = ?1 where rm.usuario.idUsuario = ?2")
    void alterarNumPesquisas(Integer regNumPesquisas, Integer idUsuario);

    @Transactional
    @Modifying
    @Query("update RegistroMedalha rm set rm.regNumPublicacoes = ?1 where rm.usuario.idUsuario = ?2")
    void alterarNumPublicacoes(Integer regNumPublicacoes, Integer idUsuario);

    @Transactional
    @Modifying
    @Query("update RegistroMedalha rm set rm.regDataInicio = 1 where rm.usuario.idUsuario = ?1")
    void alterarDataInicio(Integer idUsuario);

    Optional<RegistroMedalha> findByUsuarioIdUsuario(Integer idUsuario);

    @Transactional
    void deleteByUsuarioIdUsuario(Integer idUsuario);
}
