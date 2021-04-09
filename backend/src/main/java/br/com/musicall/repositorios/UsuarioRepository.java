package br.com.musicall.repositorios;

import br.com.musicall.dominios.InfoUsuario;
import br.com.musicall.dominios.Usuario;
import br.com.musicall.visoes.UsuarioSimples;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("select u from Usuario u where u.email like ?1 and u.senha like ?2")
    Usuario pesquisarPorEmailESenha(String email, String senha);

    @Query("select u from Usuario u where u.notificado = true")
    List<Usuario> pesquisarPorNotificado();

    @Query("select u from Usuario u where u.idUsuario = ?1")
    Usuario pesquisarPorIdUsuario(Integer idUsuario);

    @Transactional
    @Modifying
    @Query("update Usuario u set u.senha = ?1 where u.idUsuario = ?2")
    void updateSenha(String novaSenha, Integer idUsuario);

    @Query("select new br.com.musicall.visoes.UsuarioSimples(u.idUsuario, u.nome, u.email, u.senha, u.notificado) from Usuario u where u.idUsuario = ?1")
    UsuarioSimples getUsuarioSimples(Integer idUsuario);

    @Query("select u from Usuario u where u.infoUsuario.cidade = ?1")
    List<Usuario> pesquisarPorCidade(String cidade);

    @Query("select u from Usuario u where u.infoUsuario.estado = ?1")
    List<Usuario> pesquisarPorEstado(String estado);

    @Transactional
    @Modifying
    @Query("update Usuario u set u.infoUsuario.idInfoUsuario = ?1 where u.idUsuario = ?2")
    void updateInfoUsuario(Integer idInfoUsuario, Integer idUsuario);

    @Transactional
    @Modifying
    @Query("update Usuario u set u.redeSocial.idRedeSocial = ?1 where u.idUsuario = ?2")
    void updateRedeSocial(Integer idRedeSocial, Integer idUsuario);

    @Query("select u from Usuario u where u.nome = ?1")
    Usuario getUsuarioPeloNome(String nome);

}
