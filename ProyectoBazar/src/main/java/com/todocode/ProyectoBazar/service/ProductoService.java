package com.todocode.ProyectoBazar.service;

import com.todocode.ProyectoBazar.dto.ProductoDTO;
import com.todocode.ProyectoBazar.exception.NotFoundException;
import com.todocode.ProyectoBazar.mapper.Mapper;
import com.todocode.ProyectoBazar.model.Producto;
import com.todocode.ProyectoBazar.repository.IProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    private final IProductoRepository productoRepository;

    public ProductoService(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }


    @Override
    public ProductoDTO findProductoById(Long id) {
        return productoRepository.findById(id).map(Mapper::toDTO)
                .orElseThrow(()-> new NotFoundException("No se enontro el producto"));
    }

    @Override
    public List<ProductoDTO> findAllProductos() {
        return productoRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductoDTO saveProducto(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setCantidad_disponible(productoDTO.getCantidad());
        producto.setCosto(productoDTO.getCosto());
        producto.setMarca(productoDTO.getMarca());
        return Mapper.toDTO(productoRepository.save(producto));
    }

    @Override
    public ProductoDTO updateProducto(Long codigo_producto, ProductoDTO productoDTO) {
        Producto producto = productoRepository.findById(codigo_producto)
                .orElseThrow(()-> new NotFoundException("No se encontro el producto"));
        producto.setNombre(productoDTO.getNombre());
        producto.setCantidad_disponible(productoDTO.getCantidad());
        producto.setCosto(productoDTO.getCosto());
        producto.setMarca(productoDTO.getMarca());
        return Mapper.toDTO(productoRepository.save(producto));
    }

    @Override
    public ProductoDTO deleteProducto(Long codigo_producto) {
        Producto producto = productoRepository.findById(codigo_producto)
                .orElseThrow(()-> new NotFoundException("No se encontro el producto"));
        productoRepository.delete(producto);
        return Mapper.toDTO(producto);
    }
}
