package NSP_TECH.CARRITO_COMPRAS.Services;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import NSP_TECH.CARRITO_COMPRAS.model.DetalleCarrito;
import NSP_TECH.CARRITO_COMPRAS.repository.detalleCRepository;
import NSP_TECH.CARRITO_COMPRAS.services.detalleCServices;

public class DCServicesTest {
    
    @Mock
    private detalleCRepository dcRepository;
    
    @InjectMocks
    private detalleCServices dcServices;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }



    
    @Test
    public void testBuscarTodo(){
    java.util.List<DetalleCarrito> lista = new  ArrayList<>();

    DetalleCarrito Dprod1 = new DetalleCarrito();
    DetalleCarrito Dprod2 = new DetalleCarrito();

    Dprod1.setId_carrito(11L);
    Dprod1.setId_Detalle(12L);
    Dprod1.setId_producto(13L);
    Dprod1.setCantidad(100);
    Dprod1.setPrecio_unitario(10000);
    
    
    Dprod2.setId_carrito(21L);
    Dprod2.setId_Detalle(22L);
    Dprod2.setId_producto(23L);
    Dprod2.setCantidad(300);
    Dprod2.setPrecio_unitario(30000);
    

    lista.add(Dprod1);
    lista.add(Dprod2);

    when(dcRepository.findAll()).thenReturn(lista);

    java.util.List<DetalleCarrito> resultadoBusqueda = dcServices.BuscarTodoDetalle();

    assertEquals(2,resultadoBusqueda.size());
    verify(dcRepository, times(1)).findAll();

}

    @Test
    public void testBuscarUnDetalle(){
    DetalleCarrito prod = new DetalleCarrito();

    prod.setId_carrito(11L);
    prod.setId_Detalle(12L);
    prod.setId_producto(13L);
    prod.setCantidad(100);
    prod.setPrecio_unitario(10000);
    
    when(dcRepository.findById(11L)).thenReturn(Optional.of(prod));

    DetalleCarrito prodBuscado = dcServices.BuscarUnDetalle(11L);
    assertEquals(11L,prodBuscado.getId_carrito());
    verify(dcRepository, times(1)).findById(11L);

    }



    @Test
    public void testGuardarDetalle(){
        DetalleCarrito prod = new DetalleCarrito();

        prod.setId_carrito(31L);
        prod.setId_Detalle(32L);
        prod.setId_producto(33L);
        prod.setCantidad(11200);
        prod.setPrecio_unitario(2130000);
            when(dcRepository.save(any())).thenReturn(prod);

        DetalleCarrito prodGuardados = dcServices.guardarDetalle(prod);
        assertEquals(32, prodGuardados.getId_Detalle());
        verify(dcRepository, times(1)).save(prod);
    }

        @Test
        public void testEliminarDetalle(){
        Long id = 11L;
        doNothing().when(dcRepository).deleteById(id);

        dcServices.eliminarDetalle(id);

        verify(dcRepository).deleteById(id);

    }
    @Test
    public void testEditarDetalleC(){

        DetalleCarrito DCO = new DetalleCarrito();
        DCO.setId_Detalle(11L);
        DCO.setPrecio_unitario(1);
        DCO.setCantidad(15);

        DetalleCarrito DCE = new DetalleCarrito();
        DCE.setId_Detalle(11L);
        DCE.setPrecio_unitario(12);
        DCE.setCantidad(153);

        when(dcRepository.save(any(DetalleCarrito.class))).thenReturn(DCE);
        when(dcRepository.existsById(11L)).thenReturn(true);
        DetalleCarrito resultado = dcServices.guardarDetalle(DCE);

        assertNotNull(resultado);
        assertEquals(11L, resultado.getId_Detalle());
        assertEquals(12, resultado.getPrecio_unitario());
        assertEquals(153, resultado.getCantidad());

        verify(dcRepository, times(1)).save(DCE);
    }

    @Test
    public void testEliminarResena(){
        Long id = 11L;
        doNothing().when(dcRepository).deleteById(id);

        dcServices.eliminarDetalle(11L);

        verify(dcRepository, times(1)).deleteById(id);

    }
    }


