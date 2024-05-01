
package nicoangeletti.bazar.service;

import java.util.List;
import nicoangeletti.bazar.dto.ProductoDto;
import nicoangeletti.bazar.model.Producto;


public interface IProductoService {
    
    
    
    public Producto guardarProducto(ProductoDto productoDto);
    
    public List<Producto> traerProductos();
    
    public Producto traerProducto(Long idProducto);
    
    public void eliminarProducto(Long idProducto);
    
     
     public boolean existsById(Long id);
}
