
package nicoangeletti.bazar.controller;

import java.util.ArrayList;
import java.util.List;
import nicoangeletti.bazar.dto.ProductoDto;
import nicoangeletti.bazar.model.Producto;
import nicoangeletti.bazar.payload.MensajeResponse;
import nicoangeletti.bazar.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties.Endpoint;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2")
public class ProductoController {
    
    
    
      @Autowired
      IProductoService productoServ;
    
      
      @PostMapping("/productos/crear")
      public ResponseEntity<?> crearProducto(@RequestBody ProductoDto productoDto){
         Producto productoSave = productoServ.guardarProducto(productoDto);
         return ResponseEntity.ok(productoSave);
          
         
      }
      
      @GetMapping("/productos")
      public List<Producto> traerProductos(){
          List<Producto> listaProductos = productoServ.traerProductos(); 
          return listaProductos; 
          
      }
      
        @GetMapping("/productos/{codigo_producto}")
      public Producto traerProducto(@PathVariable Long codigo_producto){
        Producto producto = productoServ.traerProducto(codigo_producto);
        return producto;
          
      }
      
      
     @DeleteMapping("/productos/eliminar/{codigo_producto}")
     public String eliminarProducto(@PathVariable Long codigo_producto){
         productoServ.eliminarProducto(codigo_producto);
         return "Se ha eliminado con exito" ;
        
     }
     
  
     
     
  @PutMapping("productos/editar/{id}")
    public ResponseEntity<?> update(@RequestBody ProductoDto productoDto, @PathVariable Long id) {
        Producto productoUpdate = null;
        try {
            if (productoServ.existsById(id)) {
                productoDto.setCodigo_producto(id);
                productoUpdate = productoServ.guardarProducto(productoDto);
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Guardado correctamente")
                        .object(productoDto.builder()
                                .codigo_producto(productoUpdate.getCodigo_producto())
                                .nombre(productoUpdate.getNombre())
                                .costo(productoUpdate.getCosto())
                                .marca(productoUpdate.getMarca())
                                .cantidad_disponible(productoUpdate.getCantidad_disponible())
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



  @GetMapping("/productos/falta_stock")
public List<Producto> traerProductosEnFalta() {
    List<Producto> listaProductosEnFalta = new ArrayList<>();
    List<Producto> listaProductos = this.traerProductos(); // Obtener la lista de productos

    for (Producto producto : listaProductos) {
        if (producto.getCantidad_disponible() < 4) { // Condición para determinar si un producto está en falta de stock
            listaProductosEnFalta.add(producto); // Agregar el producto a la lista de productos en falta
        }
    }

    return listaProductosEnFalta; // Devolver la lista de productos en falta de stock
}
 
     
     
     
     
     
}


