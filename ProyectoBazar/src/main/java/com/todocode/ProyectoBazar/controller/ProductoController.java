package com.todocode.ProyectoBazar.controller;


import com.todocode.ProyectoBazar.dto.ProductoDTO;
import com.todocode.ProyectoBazar.service.IProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final IProductoService productoService;


    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("/crear")
    public ResponseEntity<ProductoDTO> createProducto(@RequestBody ProductoDTO productoDTO) {
        ProductoDTO newProducto = productoService.saveProducto(productoDTO);
        return ResponseEntity.created(URI.create("/productos/crear" + newProducto.getCodigo_producto())).body(newProducto);
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> traerProductos() {
        return ResponseEntity.ok(productoService.findAllProductos());
    }

    @GetMapping("/{codigo_producto}")
    public ResponseEntity<ProductoDTO> traerProducto(@PathVariable Long codigo_producto) {
        return ResponseEntity.ok(productoService.findProductoById(codigo_producto));
    }

    @DeleteMapping("/eliminar/{codigo_producto}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long codigo_producto) {
        productoService.deleteProducto(codigo_producto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/editar/{codigo_producto}")
    public ResponseEntity<ProductoDTO> editarProducto(@PathVariable Long codigo_producto, @RequestBody ProductoDTO productoDTO) {
        return ResponseEntity.ok(productoService.updateProducto(codigo_producto, productoDTO));
    }

    @GetMapping("/falta_stock")
    public ResponseEntity<List<ProductoDTO>> traerFaltaStock() {
        List<ProductoDTO> listaProductos = productoService.findAllProductos();
        List<ProductoDTO> bajoStock = listaProductos.stream()
                .filter(producto -> producto.getCantidad() <= 5).toList();
        return ResponseEntity.ok(bajoStock);
    }
}
