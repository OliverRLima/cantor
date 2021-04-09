package br.com.musicall.repositorios;

import br.com.musicall.dominios.Convite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface ConviteRepository extends JpaRepository<Convite, Integer> {

    @Query("select c from Convite c where c.idConvidou = ?1")
    List<Convite> getConvitesByIdConvidou(Integer idConvidou);

    @Query("select c from Convite c where c.idConvidado = ?1 order by c.dataConvite asc ")
    List<Convite> getConvitesByIdConvidado(Integer idConvidado);

    @Query("select c from Convite c where c.idConvidado = ?1 and c.idConvidou = ?2 ")
    Convite getConvitesByIdConvidadoEIdCondivou(Integer idConvidado, Integer idConvidou);

    @Transactional
    @Modifying
    @Query(value = "insert into Convite (data_convite, id_convidou, id_convidado, confirmado) values (?1,?2,?3,0);", nativeQuery = true)
    void addConvite(LocalDate dataConvite, Integer idConvidou, Integer idConvidado);

    @Transactional
    @Modifying
    @Query("update Convite c set c.confirmado = 1 where c.idConvite = ?1")
    void alterarConfirmado(Integer idConvite);

    @Transactional
    @Modifying
    @Query("update Convite c set c.confirmado = 0 where c.idConvite = ?1")
    void alterarNegado(Integer idConvite);
}
