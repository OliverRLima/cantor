package br.com.musicall.api.repositorios;

import br.com.musicall.api.dominios.RedeSocial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RedeSocialRepository extends JpaRepository<RedeSocial, Integer> {

    Optional<RedeSocial> findByFacebookAndAndTwitterAndInstagram(String facebook, String twitter, String instagram);
}
