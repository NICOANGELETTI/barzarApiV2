
package nicoangeletti.bazar.dto;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;



@Getter @Setter
@Builder
public class ClienteDto implements Serializable{
    
    private Long id_cliente;
    private String nombre; 
    private String apellido;
    private int dni; 

    public ClienteDto() {
    }

    
    
    public ClienteDto(String nombre, String apellido, int dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public ClienteDto(Long id_cliente, String nombre, String apellido, int dni) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }
    
    
    
    
    
}
