package NSP_TECH.CARRITO_COMPRAS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import NSP_TECH.CARRITO_COMPRAS.model.DetalleCarrito;

public interface detalleCRepository extends JpaRepository<DetalleCarrito, Long>{

}
