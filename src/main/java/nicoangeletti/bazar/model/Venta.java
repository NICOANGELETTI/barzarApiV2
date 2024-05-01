
package nicoangeletti.bazar.model;


import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Builder
public class Venta {
    
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)  
    private Long codigo_venta;
    private LocalDate fecha_venta;
   private Double total; 
   
   @OneToMany
   private List<Producto> listaProductos;
   @OneToOne
   Cliente unCliente; 

    public Venta(Long codigo_venta, LocalDate fecha_venta, Double total, List<Producto> listaProductos, Cliente unCliente) {
        this.codigo_venta = codigo_venta;
        this.fecha_venta = fecha_venta;
        this.total = total;
        this.listaProductos = listaProductos;
        this.unCliente = unCliente;
    }

    public Venta() {
    }
   
   
   
   
    
}
