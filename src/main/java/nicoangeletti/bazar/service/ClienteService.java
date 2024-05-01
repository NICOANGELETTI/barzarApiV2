
package nicoangeletti.bazar.service;

import java.util.List;
import nicoangeletti.bazar.dto.ClienteDto;
import nicoangeletti.bazar.model.Cliente;
import nicoangeletti.bazar.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClienteService implements IClienteService{

    
    
    @Autowired
    IClienteRepository clienteRepo;
    
    
    @Override
    public Cliente guardarCliente(ClienteDto clientedto) {
        Cliente cliente = Cliente.builder()
                .id_cliente(clientedto.getId_cliente())
                .nombre(clientedto.getNombre())
                .apellido(clientedto.getApellido())
                .dni(clientedto.getDni())
                .build();
                
        
       return clienteRepo.save(cliente);
       
        
    }

    @Override
    public List<Cliente> traerClientes() {
        List<Cliente> listaClientes = (List<Cliente>) clienteRepo.findAll();
        return listaClientes;
    }

    @Override
    public Cliente traerCliente(Long idCliente) {
       Cliente cliente = clienteRepo.findById(idCliente).orElse(null);
       return cliente; 
        
    }

    @Override
    public void eliminarCliente(Long idCliente) {
        clienteRepo.deleteById(idCliente);
    }

    
  @Override
    public boolean existsById(Long id) {
       return clienteRepo.existsById(id);
    }

 

    
    
}
