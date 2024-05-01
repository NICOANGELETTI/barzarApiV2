
package nicoangeletti.bazar.service;

import java.util.List;
import nicoangeletti.bazar.dto.ProductoDto;
import nicoangeletti.bazar.model.Cliente;
import nicoangeletti.bazar.model.Producto;
import nicoangeletti.bazar.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService{
    
    
    
    @Autowired
    IProductoRepository productoRepo; 

    @Override
    public Producto guardarProducto(ProductoDto productoDto) {
     Producto producto = Producto.builder()
             .codigo_producto(productoDto.getCodigo_producto())
             .nombre(productoDto.getNombre())
             .costo(productoDto.getCosto())
             .marca(productoDto.getMarca())
             .cantidad_disponible(productoDto.getCantidad_disponible())
             .build();
             
       return productoRepo.save(producto);
        
    }

    @Override
    public List<Producto> traerProductos() {
    List<Producto> listaProductos = productoRepo.findAll();
   
       return listaProductos;
    }

    @Override
    public Producto traerProducto(Long idProducto) {
        Producto producto = productoRepo.findById(idProducto).orElse(null);
        return producto;
    }

    @Override
    public void eliminarProducto(Long idProducto) {
        productoRepo.deleteById(idProducto);
        
    }

    @Override
    public boolean existsById(Long id) {
        return productoRepo.existsById(id);
    }

 
   
    
    
    
}
