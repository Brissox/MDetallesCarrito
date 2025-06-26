package NSP_TECH.CARRITO_COMPRAS.controller;

import java.util.List;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import NSP_TECH.CARRITO_COMPRAS.Assambler.DCModelAssembler;
import NSP_TECH.CARRITO_COMPRAS.model.DetalleCarrito;
import NSP_TECH.CARRITO_COMPRAS.services.detalleCServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api/v1/DCarrito")
public class detalleSController {

    @Autowired
    private detalleCServices dcservices;

    @Autowired
    private DCModelAssembler assambler;

     //ENDPOINT PARA BUSCAR TODOS LOS DETALLES CARRITO
    @GetMapping
    @Operation(summary = "DETALLES", description = "Operacion que lista todos los detalles")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se listaron correctamente los detalles", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalleCarrito.class))),
        @ApiResponse(responseCode = "404", description = "No se encontro ningun detalle", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se encuentran Datos")))


    })

    public ResponseEntity<?> ListarDetalle(){
        List<DetalleCarrito> carritos = dcservices.BuscarTodoDetalle();
        if (carritos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran datos");
        } else {
            return ResponseEntity.ok(assambler.toCollectionModel(carritos));
        }
    }


// ENDPOINT PARA BUSCAR UN DETALLE
    @GetMapping("/{ID_DETALLE}")

    @Operation(summary = "DETALLE", description = "Operacion que lista un detalle")
    @Parameters (value = {
        @io.swagger.v3.oas.annotations.Parameter(name="ID_DETALLE", description= "ID del detalle que se buscara", in = ParameterIn.PATH, required= true)
    })
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se lista correctamente el detalle ", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalleCarrito.class))), 
        @ApiResponse(responseCode = "404", description = "No se encontro ninguna detalle", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se encuentran Datos")))
    })
    public ResponseEntity<?> BuscarDetalle(@PathVariable Long ID_DETALLE){

        try {
            DetalleCarrito dcBuscado = dcservices.BuscarUnDetalle(ID_DETALLE);
            return ResponseEntity.ok(assambler.toModel(dcBuscado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra nigun detalle");
        }
    }


    // ENDPOINT PARA REGISTRAR UN DETALLE CARRITO
    @PostMapping
    @Operation(summary = "ENDPOINT QUE REGISTRA UN DETALLE", description = "ENDPOINT QUE REGISTRA UN DETALLE",requestBody= @io.swagger.v3.oas.annotations.parameters.RequestBody(description="SUCURSAL QUE SE VA A REGISTRAR", required = true, content = @Content(schema = @Schema(implementation = DetalleCarrito.class))))
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se registro correctamente el detalle", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalleCarrito.class))),
        @ApiResponse(responseCode = "500", description = "Indica que no se logro registrar el detalle", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se puede registrar el carrito")))
    })

    public ResponseEntity<?> GuardarCDetalle(@RequestBody DetalleCarrito dcGuardar){
    try {
            DetalleCarrito dcRegistrar = dcservices.guardarDetalle(dcGuardar);
            return ResponseEntity.ok((assambler.toModel(dcGuardar)));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("No se puede registrar el carrito");
    }
    }


    // ENDPOINT PARA EDITAR UN DETALLE

    @PutMapping("/{ID_DETALLE}") //SOLO PERMITE ACTUALIZAR ESCRIBIENDO TODOS LOS DATOS

    @Operation(summary = "ENDPOINT QUE EDITA UN DETALLE", description = "ENDPOINT QUE EDITA UN DETALLE", requestBody=@io.swagger.v3.oas.annotations.parameters.RequestBody(description="DETALLE QUE SE VA A ACTUALIZAR", required = true, content = @Content(schema = @Schema(implementation = DetalleCarrito.class))))
    @Parameters (value = {
        @io.swagger.v3.oas.annotations.Parameter(name="ID_DETALLE", description= "ID del detalle que se editara", in = ParameterIn.PATH, required= true)})
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se edito correctamente el detalle", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalleCarrito.class))),
        @ApiResponse(responseCode = "500", description = "Detalle no registrado", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se puede registrar el detalle")))
    })


        
    public ResponseEntity<?> ActualizarDetalle(@PathVariable Long ID_DETALLE, @RequestBody DetalleCarrito dcActualizar){
        try {
            DetalleCarrito dcActualizado = dcservices.BuscarUnDetalle(ID_DETALLE);
            dcActualizado.setId_carrito(dcActualizar.getId_carrito());
            dcActualizado.setId_producto(dcActualizar.getId_producto());
            dcActualizado.setPrecio_unitario(dcActualizar.getPrecio_unitario());
            dcservices.guardarDetalle(dcActualizado);
            return ResponseEntity.ok((assambler.toModel(dcActualizado)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sucursal no registrada");
        }
    }


  // ENDPOINT PARA ELIMINAR UN DETALLE



    @DeleteMapping("/{ID_DETALLE}")
    
    @Operation(summary = "ENDPOINT QUE ELIMINA UN DETALLE", description = "ENDPOINT QUE ELIMINA UN DETALLE", requestBody=@io.swagger.v3.oas.annotations.parameters.RequestBody(description="DETALLE QUE SE VA A ELIMINAR", required = true, content = @Content(schema = @Schema(implementation = DetalleCarrito.class))))
    @Parameters (value = {
        @io.swagger.v3.oas.annotations.Parameter(name="ID_DETALLE", description= "ID del detalle que se eliminara", in = ParameterIn.PATH, required= true)})
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se elimino correctamente el detalle", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalleCarrito.class))),
        @ApiResponse(responseCode = "500", description = "Detalle no registrado", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se puede eliminar el detalle")))
    })



        public ResponseEntity<String> EliminarDetalle(@PathVariable Long ID_DETALLE){
            try {
                DetalleCarrito DetalleCBuscado = dcservices.BuscarUnDetalle(ID_DETALLE);
                dcservices.eliminarDetalle(ID_DETALLE);
                return ResponseEntity.status(HttpStatus.OK).body("Se elimina detalle");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Detalle no esta registrado");
            }

}
}
