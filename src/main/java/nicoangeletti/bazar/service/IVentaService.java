
package nicoangeletti.bazar.service;

import java.util.List;
import nicoangeletti.bazar.dto.VentaDto;
import nicoangeletti.bazar.model.Venta;


public interface IVentaService {
    
    
    public Venta guardarVenta(VentaDto ventaDto);
    
    public List<Venta> traerVentas();
    
    public Venta traerVenta(Long idVenta);
    
    public void eliminarVenta(Long idVenta);
    
    
     public boolean existsById(Long id);
  
}
