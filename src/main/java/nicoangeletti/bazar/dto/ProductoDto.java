
package nicoangeletti.bazar.dto;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;




@Getter @Setter
@Builder
public class ProductoDto implements Serializable{
    
  private Long codigo_producto;
  private String nombre;
  private String marca;
  private Double costo; 
  private Double cantidad_disponible;

    public ProductoDto() {
    }

  
  
  
    public ProductoDto(Long codigo_producto, String nombre, String marca, Double costo, Double cantidad_disponible) {
        this.codigo_producto = codigo_producto;
        this.nombre = nombre;
        this.marca = marca;
        this.costo = costo;
        this.cantidad_disponible = cantidad_disponible;
    }

  
  
}
