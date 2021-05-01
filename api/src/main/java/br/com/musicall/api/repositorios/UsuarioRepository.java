package br.com.musicall.api.repositorios;

import br.com.musicall.api.dominios.InfoUsuario;
import br.com.musicall.api.dominios.RedeSocial;
import br.com.musicall.api.dominios.Usuario;
import br.com.musicall.api.dto.InfoUsuarioDto;
import br.com.musicall.api.dto.RedeSocialDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmailAndSenha(String email, String senha);

    @Transactional
    @Modifying
    @Query("update Usuario u set u.infoUsuario = ?1 where u.idUsuario = ?2")
    void alterarInfoUsuarioPorId(InfoUsuario infoUsuario, Integer idUsuario);

    @Transactional
    @Modifying
    @Query("update Usuario u set u.redeSocial = ?1 where u.idUsuario = ?2")
    void alterarRedeSocialPorId(RedeSocial redeSocial, Integer idUsuario);
}
