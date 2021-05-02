package br.com.musicall.api.repositorios;

import br.com.musicall.api.dominios.RedeSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface RedeSocialRepository extends JpaRepository<RedeSocial, Integer> {

    @Transactional
    @Modifying
    @Query("update RedeSocial r set r.facebook = ?1, r.instagram = ?2, r.twitter = ?3 where r.idRedeSocial = ?4")
    void alterarSocialPorid(String facebook, String instagram, String telefone, Integer idRedeSocial);
}
