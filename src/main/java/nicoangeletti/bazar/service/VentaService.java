
package nicoangeletti.bazar.service;

import java.util.List;
import nicoangeletti.bazar.dto.VentaDto;
import nicoangeletti.bazar.model.Venta;
import nicoangeletti.bazar.repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VentaService implements IVentaService  {
    
    
    
    @Autowired
    IVentaRepository ventaRepo;

    @Override
    public Venta guardarVenta(VentaDto ventaDto) {
        Venta venta = Venta.builder()
         .codigo_venta(ventaDto.getCodigo_venta())
         .fecha_venta(ventaDto.getFecha_venta())
         .listaProductos(ventaDto.getListaProductos())
         .unCliente(ventaDto.getUnCliente())
         .total(ventaDto.getTotal())
         .build();
        
        
        
        return ventaRepo.save(venta);
    }

    @Override
    public List<Venta> traerVentas() {
        List<Venta> listaVentas = ventaRepo.findAll();
        return listaVentas; 
    }

    @Override
    public Venta traerVenta(Long idVenta) {
        Venta venta = ventaRepo.findById(idVenta).orElse(null);
        return venta;
                }

    @Override
    public void eliminarVenta(Long idVenta) {
       ventaRepo.deleteById(idVenta);
    }

  

    @Override
    public boolean existsById(Long id) {
        return ventaRepo.existsById(id);
    }

   

    
    
    
    
}
