package br.com.musicall.repositorios;

import br.com.musicall.dominios.InfoUsuario;
import br.com.musicall.dominios.RedeSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDate;

public interface RedeSocialRepository extends JpaRepository<RedeSocial, Integer> {

    @Query("select rs from RedeSocial rs where rs.idRedeSocial = ?1")
    RedeSocial getPorId(Integer idRedeSocial);

    @Transactional
    @Modifying
    @Query("update RedeSocial r set r.facebook = ?1 where r.idRedeSocial = ?2")
    void alterarFacebookPorId(String facebook, int idRedeSocial);

    @Transactional
    @Modifying
    @Query("update RedeSocial r set r.instagram = ?1 where r.idRedeSocial = ?2")
    void alterarInstagramPorId(String facebook, int idRedeSocial);

    @Transactional
    @Modifying
    @Query("update RedeSocial r set r.twitter = ?1 where r.idRedeSocial = ?2")
    void alterarTwitterPorId(String facebook, int idRedeSocial);

    @Transactional
    @Modifying
    @Query("update RedeSocial r set r.facebook = ?1, r.instagram = ?2, r.twitter = ?3 where r.idRedeSocial = ?4")
    void alterarTodasPorId(String facebook, String instagram, String telefone, Integer idRedeSocial);

    @Transactional
    @Modifying
    @Query("update RedeSocial r set r.twitter = '',r.instagram = '', r.facebook = '' where r.idRedeSocial = ?1")
    void apagarConteudoPorId(int idRedeSocial);

    @Transactional
    @Modifying
    @Query(value = "insert into rede_social (facebook, instagram, twitter) values (?1,?2,?3);", nativeQuery = true)
    void adicionarRedeSocial(String facebook, String instagram, String twitter);

    @Query("select rs from RedeSocial rs where rs.facebook = ?1 and rs.instagram = ?2 and rs.twitter = ?3")
    RedeSocial getRedeSocialSegundoParametros(String facebook, String instagram, String twitter);
}
