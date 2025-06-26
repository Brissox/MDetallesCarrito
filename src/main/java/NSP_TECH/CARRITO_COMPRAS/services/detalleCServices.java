package NSP_TECH.CARRITO_COMPRAS.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import NSP_TECH.CARRITO_COMPRAS.model.DetalleCarrito;
import NSP_TECH.CARRITO_COMPRAS.repository.detalleCRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class detalleCServices {

    @Autowired
    private detalleCRepository detallecrepository;

    
    public void eliminarDetalle(Long id_detalle ) {
        detallecrepository.deleteById(id_detalle);
    }

    public List<DetalleCarrito> BuscarTodoDetalle(){
        return detallecrepository.findAll();
    }

    public DetalleCarrito BuscarUnDetalle(Long idDetalle){
    return detallecrepository.findById(idDetalle).get();
    }

    public DetalleCarrito guardarDetalle(DetalleCarrito carritoD) {
        return detallecrepository.save(carritoD);
    }

    public List<DetalleCarrito> BuscarUnCarrito(Long ID_CARRITO) {
    return detallecrepository.buscarPorIdCarrito(ID_CARRITO);
    }
}