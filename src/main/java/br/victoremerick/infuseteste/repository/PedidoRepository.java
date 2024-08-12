package br.victoremerick.infuseteste.repository;

import br.victoremerick.infuseteste.model.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    boolean existsByNumeroControle(String numeroControle);

    @Query("SELECT p FROM Pedido p WHERE " +
            "(:numeroControle IS NULL OR p.numeroControle = :numeroControle) AND " +
            "(:dataCadastro IS NULL OR p.dataCadastro = :dataCadastro)")
    List<Pedido> findByFilters(@Param("numeroControle") String numeroControle,
                               @Param("dataCadastro") LocalDate dataCadastro);

}
