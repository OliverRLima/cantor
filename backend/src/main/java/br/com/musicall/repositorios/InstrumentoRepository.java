package br.com.musicall.repositorios;

import br.com.musicall.dominios.Instrumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface InstrumentoRepository extends JpaRepository<Instrumento, Integer> {

    @Transactional
    @Modifying
    @Query("delete from Instrumento i where i.usuario.idUsuario = ?1")
    void deletarPeloIdUsuario(Integer idUsuario);

    @Transactional
    @Modifying
    @Query("update Instrumento i set i.nvDominio = ?1 where i.idInstrumento = ?2")
    void alterarNvDominioPorId(String nvDominio, Integer idInstrumento);

    @Transactional
    @Modifying
    @Query("update Instrumento i set i.instrumento = ?1 where i.idInstrumento = ?2")
    void alterarInstrumentoPorId(String instrumento, Integer idInstrumento);

    @Transactional
    @Modifying
    @Query(value = "insert into instrumento (instrumento, nv_dominio, tipo_instrumento, usuario_id_usuario) values (?1,?2,?3,?4);", nativeQuery = true)
    void adicionarInstrumento(String instrumentoMusical, String nvDominio, String tipoInstrumento, Integer idUsuario);

    @Query("select i from Instrumento i where i.usuario.idUsuario = ?1")
    List<Instrumento> selecionarPeloIdUsuario(Integer idUsuario);

    @Query("select i from Instrumento i where i.instrumento = ?1")
    List<Instrumento> selecionarTodosInstrumentos(String idUsuario);

}
