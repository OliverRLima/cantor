package br.com.musicall.api.repositorios;

import br.com.musicall.api.dominios.InfoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InfoUsuarioRepository extends JpaRepository<InfoUsuario,Integer> {

    List<InfoUsuario> findByEstado(String estado);

    List<InfoUsuario> findByCidade(String cidade);

    @Transactional
    @Modifying
    @Query("update InfoUsuario i set i.dataAniversario = ?1, i.estado = ?2, i.cidade = ?3  where i.idInfoUsuario = ?4")
    void alterarInfoPorId(LocalDate dataAniversario, String estado, String cidade, Integer idInfo);

}
