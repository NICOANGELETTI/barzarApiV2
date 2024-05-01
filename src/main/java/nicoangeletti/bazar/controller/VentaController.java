
package nicoangeletti.bazar.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import nicoangeletti.bazar.dto.ClaseDTO;
import nicoangeletti.bazar.dto.VentaDto;
import nicoangeletti.bazar.model.Cliente;
import nicoangeletti.bazar.model.Producto;
import nicoangeletti.bazar.model.Venta;
import nicoangeletti.bazar.payload.MensajeResponse;
import nicoangeletti.bazar.service.IClienteService;
import nicoangeletti.bazar.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
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
public class VentaController {
    
    
    
    
    @Autowired
    IVentaService ventaServ;
    
    @Autowired
    IClienteService clienteService;
    
    
  
@PostMapping("/ventas/crear")
public ResponseEntity<?> guardarVenta(@RequestBody VentaDto ventaDto) {
   
    Venta ventaSave = null;
    try {
        
         ventaSave = ventaServ.guardarVenta(ventaDto);
        return new ResponseEntity<>(MensajeResponse.builder()
        .mensaje("Guardado Correctamente")
        .object(ventaDto.builder()
               .codigo_venta(ventaSave.getCodigo_venta())
               .fecha_venta(ventaSave.getFecha_venta())
               .total(ventaSave.getTotal())
               .unCliente(ventaSave.getUnCliente())
               .listaProductos(ventaSave.getListaProductos())
               .build())
        .build() , HttpStatus.CREATED
        );
        
      
    } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    
@GetMapping("/ventas")
public List<Venta> traerVentas(){
    List<Venta> listaVentas = ventaServ.traerVentas();
    return listaVentas;
}

@GetMapping("/ventas/{codigo_venta}")
public Venta traerVenta(@PathVariable Long codigo_venta){
    Venta venta = ventaServ.traerVenta(codigo_venta);
    return venta;
    
}

@DeleteMapping("/ventas/eliminar/{codigo_venta}")
public String eliminarVenta(@PathVariable Long codigo_venta){
    ventaServ.eliminarVenta(codigo_venta);
    return "Se ha eliminado con exito";
}

@PutMapping("/ventas/editar/{codigo_venta}")
public ResponseEntity<?> editarVenta(@RequestBody VentaDto ventaDto, @PathVariable Long id){
    
    Venta ventaUpdate = null;
        try {
            if (ventaServ.existsById(id)) {
                ventaDto.setCodigo_venta(id);
                ventaUpdate = ventaServ.guardarVenta(ventaDto);
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Guardado correctamente")
                        .object(ventaDto.builder()
                               .codigo_venta(ventaUpdate.getCodigo_venta())
                               .fecha_venta(ventaUpdate.getFecha_venta())
                                .total(ventaUpdate.getTotal())
                                .unCliente(ventaUpdate.getUnCliente())
                                .listaProductos(ventaUpdate.getListaProductos())
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

    
   
    

    
    


    @GetMapping("/ventas/productos/{codigo_venta}")
    public List<Producto> traerProductosVenta(@PathVariable Long codigo_venta){
        List<Venta> listaVentas = this.traerVentas();
        List<Producto> listaProductos = new ArrayList<Producto>();
        
        for(Venta venta : listaVentas){
            if(venta.getCodigo_venta() == codigo_venta){
                listaProductos = venta.getListaProductos();
            }
        }
        return listaProductos;
    }
        
        
    

@GetMapping("/ventas/traerDatos/{fecha_venta}")
public String obtenerDatosVenta(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha_venta){
    List<Venta> listaVentas = this.traerVentas(); 
    
    Double montoTotal = 0.0 ; 
    int ventasTotal = 0;
    
    for(Venta venta : listaVentas){
        if(venta.getFecha_venta().equals(fecha_venta)){
            montoTotal += venta.getTotal();
            ventasTotal ++;
        }
        
    }
    
    return "Datos de las ventas del " + fecha_venta + " : Monto total =  " + montoTotal + " Ventas Totales= " + ventasTotal; 
}


@GetMapping("/ventas/mayor_venta")
public ClaseDTO traerDatosMayorVenta(){
    List<Venta> listaVentas = this.traerVentas();
    Double montoMayor = 0.00;
    ClaseDTO mayorVenta = null;
    
    for(Venta venta : listaVentas){
        if(venta.getTotal() > montoMayor){
            montoMayor = venta.getTotal(); 
            
            mayorVenta = new ClaseDTO();
            mayorVenta.setCodigo_venta(venta.getCodigo_venta());
            mayorVenta.setCantidad_productos(venta.getListaProductos().size());
            mayorVenta.setNombre_cliente(venta.getUnCliente().getNombre());
            mayorVenta.setApellido_cliente(venta.getUnCliente().getApellido());
            mayorVenta.setTotal(montoMayor);
                    
                          }
    }
    
    return mayorVenta;
}

  
  
  
}
