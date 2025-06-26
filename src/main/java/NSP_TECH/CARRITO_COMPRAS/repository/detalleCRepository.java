package NSP_TECH.CARRITO_COMPRAS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import NSP_TECH.CARRITO_COMPRAS.model.DetalleCarrito;

public interface detalleCRepository extends JpaRepository<DetalleCarrito, Long>{

    @Query("SELECT d FROM DetalleCarrito d WHERE d.id_carrito = :id_carrito")
List<DetalleCarrito> buscarPorIdCarrito(@Param("id_carrito") Long id_carrito);

}
