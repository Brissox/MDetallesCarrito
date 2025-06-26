package NSP_TECH.CARRITO_COMPRAS.Assambler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import NSP_TECH.CARRITO_COMPRAS.controller.detalleSController;
import NSP_TECH.CARRITO_COMPRAS.model.DetalleCarrito;


@Component
public class DCModelAssembler implements RepresentationModelAssembler<DetalleCarrito, EntityModel<DetalleCarrito>>{

    @Override
    public EntityModel<DetalleCarrito> toModel(DetalleCarrito dc){
        return EntityModel.of(
            dc,
            linkTo(methodOn(detalleSController.class).BuscarDetalle(dc.getId_Detalle())).withRel("LINKS"),
            linkTo(methodOn(detalleSController.class).ListarDetalle()).withRel("todas-los-detalles"),
            linkTo(methodOn(detalleSController.class).ActualizarDetalle(dc.getId_Detalle(), dc)).withRel("actualiza-un-detalle"),
            linkTo(methodOn(detalleSController.class).EliminarDetalle(dc.getId_Detalle())).withRel("elimina-un-detalle")
        );
    }


}
