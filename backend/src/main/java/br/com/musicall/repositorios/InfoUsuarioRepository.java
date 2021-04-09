package br.com.musicall.repositorios;

import br.com.musicall.dominios.InfoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface InfoUsuarioRepository extends JpaRepository<InfoUsuario,Integer> {

    @Transactional
    @Modifying
    @Query("update InfoUsuario i set i.dataAniversario = ?1 where i.idInfoUsuario = ?2")
    void alterarAniversarioPorId(LocalDate date, Integer idInfo);

    @Transactional
    @Modifying
    @Query("update InfoUsuario i set i.descricao = ?1 where i.idInfoUsuario = ?2")
    void alterarDescricaoPorId(String descricao, Integer idInfo);

    @Transactional
    @Modifying
    @Query("update InfoUsuario i set i.estado = ?1 where i.idInfoUsuario = ?2")
    void alterarEstadoPorId(String estado, Integer idInfo);

    @Transactional
    @Modifying
    @Query("update InfoUsuario i set i.cidade = ?1 where i.idInfoUsuario = ?2")
    void alterarCidadePorId(String cidade, Integer idInfo);

    @Transactional
    @Modifying
    @Query("update InfoUsuario i set i.cidade = ?1, i.dataAniversario = ?2, i.estado = ?3 where i.idInfoUsuario = ?4")
    void alterarInfoPorId(String cidade,LocalDate dataAniversario, String estado, Integer idInfo);

    @Query("select i from InfoUsuario i where i.idInfoUsuario = ?1")
    InfoUsuario getInfo(Integer idInfoUsuario);

    @Query("select i from InfoUsuario i where i.cidade = ?1 and i.descricao = ?2 and i.dataAniversario = ?3")
    InfoUsuario getInfoSegundoParametros(String cidade, String descricao, LocalDate dataAniversario);

    @Query("select i from InfoUsuario i where i.cidade = ?1")
    List<InfoUsuario> getInfoUsuarioPorCidade(String idInfoUsuario);

    @Transactional
    @Modifying
    @Query(value = "insert into info_usuario (cidade, data_aniversario, descricao, estado) values (?1,?2,?3,?4);", nativeQuery = true)
    void adicionarInfoUsuario(String cidade, LocalDate dataAniversario, String descricao, String estado);
}
