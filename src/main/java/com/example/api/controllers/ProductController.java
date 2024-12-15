package com.example.api.controllers;

import com.example.api.Exception.RecordNotFoundException;
import com.example.api.model.DTO.Producto_Request_Update;
import com.example.api.model.Empresa;
import com.example.api.model.Producto;
import com.example.api.services.EmpresaService;
import com.example.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productoService;
    @Autowired
    private EmpresaService empresaservice;

    @GetMapping
    public List<Producto> obtenerTodosLosProductos() {
        return productoService.obtenerTodosLosProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductById(@PathVariable("id") Integer id)
            throws RecordNotFoundException {
        Optional<Producto> entity = productoService.obtenerProductoPorId(id);

        if (!entity.isPresent()) {
            return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Producto>(entity.get(), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<List<Producto>> getProductByCompany(@PathVariable("id") Integer id)
            throws RecordNotFoundException {
        List<Producto> entity = productoService.obtenerTodosLosProductosPorIdEmpresa(id);
        System.out.println(id);
        System.out.println(entity);
        if (entity.isEmpty()) {
            return new ResponseEntity<List<Producto>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Producto>>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Producto> getProductByName(@PathVariable("name") String name)
            throws RecordNotFoundException {
        List<Producto> entity = productoService.obtenerProductoPorNombre(name);

        if (entity.isEmpty()) {
            return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Producto>(entity.get(0), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Producto>> getProductByType(@PathVariable("type") String type)
            throws RecordNotFoundException {
        List<Producto> entity = productoService.obtenerProductoPorTipo(type);

        if (entity.isEmpty()) {
            return new ResponseEntity<List<Producto>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Producto>>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/company/{empresaId}/nombre/{nombre}")
    public ResponseEntity< List<Producto>> getProductsByNombreAndEmpresaId(@PathVariable int empresaId, @PathVariable String nombre) {
        List<Producto> entity = productoService.getProductsByNombreAndEmpresaId(nombre, empresaId);
        return ResponseEntity.ok(entity);
    }

    @PostMapping("/addProduct")
    public Producto guardarProducto(@RequestBody Producto producto) {
        return productoService.guardarProducto(producto);
    }
    @PostMapping("/guardar")
    public Producto guardarProducto(@RequestParam("nombre") String nombre,
                                    @RequestParam("descripcion") String descripcion,
                                    @RequestParam("precio") Integer precio,
                                    @RequestParam("tipo") String tipo,
                                    @RequestParam("idEmpresa") Integer idEmpresa,  // Recibe el ID de la empresa
                                    @RequestParam("imagen") MultipartFile imagen) throws IOException {
        // Obtener la empresa por su ID
        Optional<Empresa> empresaOptional = empresaservice.GetCompanyById(idEmpresa);

        // Verificar si la empresa fue encontrada
        if (!empresaOptional.isPresent()) {
            // Si no se encuentra la empresa, retornar un error (por ejemplo, 404 Not Found)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa no encontrada");
        }

        Empresa empresa = empresaOptional.get();  // Obtener la empresa desde el Optional

        Producto producto = new Producto();
        producto.setNombre(nombre);  // Establecer el nombre
        producto.setDescripcion(descripcion);  // Establecer la descripción
        producto.setPrecio(precio);  // Establecer el precio
        producto.setTipo(tipo);  // Establecer el tipo
        producto.setIdEmpresa(empresa);  // Establecer la empresa

        // Llamar al servicio para guardar el producto (incluyendo la imagen)
        return productoService.guardarProductoConImagen(producto, imagen);
    }
    @PutMapping("/editProductWithImage/{id}")
    public ResponseEntity<Producto> updateProductWithImage(@PathVariable("id") Integer id,
                                                           @RequestParam("nombre") String nombre,
                                                           @RequestParam("precio") Integer precio,
                                                           @RequestParam("imagen") MultipartFile imagen) throws IOException {

        Producto producto = productoService.obtenerProductoPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));
        
        // Actualizar los datos del producto
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        
        if (imagen != null && !imagen.isEmpty()) {
            // Si se envía una nueva imagen, manejarla
            producto.setImagen(imagen.getBytes());
        }
        
        Producto updatedProduct = productoService.save(producto);
        return ResponseEntity.ok(updatedProduct);
    }





    //metodo para actualizar producto segun el repo y el servicio

    @PutMapping("/edit")
    public void actualizarProducto(@RequestBody Producto_Request_Update producto) {
        System.out.println(producto);
        productoService.actualizarProducto(producto);
    }


    @DeleteMapping("/delete/{id}")
    public void eliminarProducto(@PathVariable Integer id) {
        productoService.eliminarProducto(id);
        System.out.println("Eliminado pc");
    }
}
