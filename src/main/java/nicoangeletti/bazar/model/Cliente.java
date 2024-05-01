
package nicoangeletti.bazar.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@Entity
@Builder
public class Cliente {
    
    
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)  
    private Long id_cliente;
    private String nombre; 
    private String apellido;
    private int dni; 

    public Cliente(Long id_cliente, String nombre, String apellido, int dni) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public Cliente() {
    }
  
    
}
