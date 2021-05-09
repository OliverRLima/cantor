package br.com.musicall.api.repositorios;

import br.com.musicall.api.dominios.Convite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ConviteRepository extends JpaRepository<Convite, Integer> {

    @Transactional
    void deleteByIdConvidado(Integer idUsuario);

    @Transactional
    void deleteByIdConvidou(Integer idUsuario);

    List<Convite> findAllByIdConvidado(Integer idConvidado);

    List<Convite> findAllByIdConvidou(Integer idConvidado);

    Optional<Convite> findByIdConvidouAndIdConvidado(Integer idConvidou, Integer idConvidado);

    @Transactional
    @Modifying
    @Query("update Convite c set c.confirmado = 1 where c.idConvite = ?1")
    void alterarConfirmado(Integer idConvite);

    @Transactional
    @Modifying
    @Query("update Convite c set c.confirmado = 0 where c.idConvite = ?1")
    void alterarNegado(Integer idConvite);

    @Transactional
    @Modifying
    @Query(value = "insert into Convite (data_convite, id_convidou, id_convidado, confirmado) values (?1,?2,?3,0);", nativeQuery = true)
    void criarConvite(LocalDate dataConvite, Integer idConvidou, Integer idConvidado);
}
