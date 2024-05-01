
package nicoangeletti.bazar.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import nicoangeletti.bazar.model.Cliente;
import nicoangeletti.bazar.model.Producto;

@Getter @Setter
@Builder
public class VentaDto implements Serializable {

    private Long codigo_venta;
    private LocalDate fecha_venta;
    private Double total;

    @OneToMany
    private List<Producto> listaProductos;
    @OneToOne
    Cliente unCliente;

    public VentaDto() {
    }

    public VentaDto(Long codigo_venta, LocalDate fecha_venta, Double total, List<Producto> listaProductos, Cliente unCliente) {
        this.codigo_venta = codigo_venta;
        this.fecha_venta = fecha_venta;
        this.total = total;
        this.listaProductos = listaProductos;
        this.unCliente = unCliente;
    }

   


   
   
  
    
}
