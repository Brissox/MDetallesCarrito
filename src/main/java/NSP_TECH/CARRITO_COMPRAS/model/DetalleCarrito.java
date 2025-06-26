package NSP_TECH.CARRITO_COMPRAS.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="carrito_detalle")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description="")

public class DetalleCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DETALLE")
    @Schema(description = "Identificador único del detalle del carrito", example = "11")
    private Long id_Detalle;

    @Column(name = "ID_CARRITO")
    @Schema(description = "Identificador del carrito al que pertenece este detalle", example = "10")
    private Long id_carrito;

    @Column(name = "ID_PRODUCTO")
    @Schema(description = "Identificador del producto agregado al carrito", example = "5")
    private Long id_producto;

    @Column(name = "CANTIDAD")
    @Schema(description = "Cantidad de unidades del producto en este detalle", example = "3")
    private Integer cantidad;

    @Column(name = "PRECIO_UNITARIO")
    @Schema(description = "Precio unitario del producto al momento de agregarlo al carrito", example = "19990")
    private int precio_unitario;

    

}
