
package nicoangeletti.bazar.service;

import java.util.List;
import nicoangeletti.bazar.dto.ClienteDto;
import nicoangeletti.bazar.model.Cliente;



public interface IClienteService {
    
    
    public Cliente guardarCliente(ClienteDto clientedto);
    
    public List<Cliente> traerClientes();
    
    public Cliente traerCliente(Long idCliente);
    
    public void eliminarCliente(Long idCliente);
    
    
       public boolean existsById(Long id);
}
