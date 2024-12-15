package com.example.api.services;

import com.example.api.Repositories.ProductoRepository;
import com.example.api.model.DTO.Producto_Request_Update;
import com.example.api.model.Empresa;
import com.example.api.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public List<Producto> obtenerTodosLosProductosPorIdEmpresa(int id) {return productoRepository.getByCompany(id);}

    public Optional<Producto> obtenerProductoPorId(Integer id) {
        return productoRepository.findById(id);
    }

    public List<Producto> obtenerProductoPorNombre(String nombre) {
        return productoRepository.getByName(nombre);
    }

    public List<Producto> obtenerProductoPorTipo(String tipo) {
        return productoRepository.getByType(tipo);
    }

    public List<Producto> getProductsByNombreAndEmpresaId(String nombre, int id) {
        return productoRepository.findByNombreAndEmpresaId(nombre, id);
    }

    public Producto guardarProducto(Producto producto) {
        System.out.println("ADDING");
        if (producto.getImagen() == null) {
            producto.setImagen(new byte[0]);
        }
        System.out.println(producto);
        return productoRepository.save(producto);
    }
    public Producto guardarProductoConImagen(Producto producto, MultipartFile imagen) throws IOException {
        if (imagen != null && !imagen.isEmpty()) {
            // Convertir el archivo de imagen en un array de bytes
            byte[] imgBytes = imagen.getBytes();
            producto.setImagen(imgBytes);  // Establecer la imagen en el producto
        }
        
        // Guardar el producto (incluyendo la imagen) en la base de datos
        return productoRepository.save(producto);
    }

    //Metodo para actualizar un producto
    public void actualizarProducto(Producto_Request_Update producto) {
        System.out.println(producto);
        productoRepository.actualizarProducto(producto.getNombre(), producto.getPrecio(), producto.getProductoId());
    }
    public void eliminarProducto(Integer id) {
        productoRepository.deleteProductById(id);
        System.out.println("Eliminado ps");
    }
}

