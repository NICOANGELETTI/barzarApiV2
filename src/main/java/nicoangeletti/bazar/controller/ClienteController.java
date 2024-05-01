
package nicoangeletti.bazar.controller;

import java.util.List;
import nicoangeletti.bazar.dto.ClienteDto;
import nicoangeletti.bazar.model.Cliente;
import nicoangeletti.bazar.payload.MensajeResponse;
import nicoangeletti.bazar.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
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


@RestController
@RequestMapping("/api/v2")
public class ClienteController {
    
    
    
    @Autowired
    IClienteService clienteServ;
    
    





@GetMapping("/clientes")
public List<Cliente> traerClientes(){
    List<Cliente> listaClientes = clienteServ.traerClientes();
    return listaClientes; 
    
}

    
@GetMapping("/clientes/{id_cliente}")
public Cliente traerCliente(@PathVariable Long id_cliente){
    Cliente cliente = clienteServ.traerCliente(id_cliente);
    return cliente; 
}
    



@PostMapping("/clientes/crear")
public ResponseEntity<?> guardarCliente(@RequestBody ClienteDto clienteDto) {
    Cliente clienteSave = null;
    try{
        clienteSave = clienteServ.guardarCliente(clienteDto);
        return new ResponseEntity<>(MensajeResponse.builder()
        .mensaje("Guardado Correctamente")
        .object(clienteDto.builder()
                  .id_cliente(clienteSave.getId_cliente())
                  .nombre(clienteSave.getNombre())
                  .apellido(clienteSave.getApellido())
                  .dni(clienteSave.getDni())
                  .build())
                .build(), HttpStatus.CREATED
           
        
        );
    }catch(DataAccessException exDt){
       return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    } 
    




 


@DeleteMapping("/clientes/eliminar/{id_cliente}")
public String eliminarCliente(@PathVariable Long id_cliente){
    clienteServ.eliminarCliente(id_cliente);
    return "Se ha eliminado con exito al cliente"; 
}

 

 @PutMapping("cliente/editar/{id}")
    public ResponseEntity<?> update(@RequestBody ClienteDto clienteDto, @PathVariable Long id) {
        Cliente clienteUpdate = null;
        try {
            if (clienteServ.existsById(id)) {
                clienteDto.setId_cliente(id);
                clienteUpdate = clienteServ.guardarCliente(clienteDto);
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Guardado correctamente")
                        .object(ClienteDto.builder()
                                .id_cliente(clienteUpdate.getId_cliente())
                                .nombre(clienteUpdate.getNombre())
                                .apellido(clienteUpdate.getApellido())
                                .dni(clienteUpdate.getDni())
                                .build())
                        .build()
                        , HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("El registro que intenta actualizar no se encuentra en la base de datos.")
                                .object(null)
                                .build()
                        , HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

@DeleteMapping("/cliente/eliminar/{id}")
public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
    try {
        clienteServ.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    } catch (EmptyResultDataAccessException ex) {
        return ResponseEntity.notFound().build();
    } catch (DataAccessException ex) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }
}


         
}
