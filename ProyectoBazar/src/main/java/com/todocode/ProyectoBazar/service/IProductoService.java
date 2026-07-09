package com.todocode.ProyectoBazar.service;

import com.todocode.ProyectoBazar.dto.ProductoDTO;

import java.util.List;

public interface IProductoService {
    public ProductoDTO findProductoById(Long id);
    public List<ProductoDTO> findAllProductos();
    public ProductoDTO saveProducto(ProductoDTO productoDTO);
    public ProductoDTO updateProducto(Long codigo_producto,ProductoDTO productoDTO);
    public ProductoDTO deleteProducto(Long codigo_producto);
}
