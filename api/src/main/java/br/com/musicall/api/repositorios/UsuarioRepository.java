package br.com.musicall.api.repositorios;

import br.com.musicall.api.dominios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByInfoUsuarioIdInfoUsuario(Integer idInfoUsuario);

    List<Usuario> findByNome(String nome);

    @Transactional
    @Modifying
    @Query("update Usuario u set u.infoUsuario.idInfoUsuario = ?1 where u.idUsuario = ?2")
    void updateInfoUsuario(Integer idInfoUsuario, Integer idUsuario);

    @Transactional
    @Modifying
    @Query("update Usuario u set u.redeSocial.idRedeSocial = ?1 where u.idUsuario = ?2")
    void updateRedeSocial(Integer idRedeSocial, Integer idUsuario);

    @Transactional
    @Modifying
    @Query("update Usuario u set u.senha = ?1 where u.idUsuario = ?2")
    void updateSenha(String novaSenha, Integer idUsuario);
}
