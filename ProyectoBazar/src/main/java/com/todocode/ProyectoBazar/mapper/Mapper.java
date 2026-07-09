package com.todocode.ProyectoBazar.mapper;

import com.todocode.ProyectoBazar.dto.ClienteDTO;
import com.todocode.ProyectoBazar.dto.ProductoDTO;
import com.todocode.ProyectoBazar.dto.VentaDTO;
import com.todocode.ProyectoBazar.model.Cliente;
import com.todocode.ProyectoBazar.model.Producto;
import com.todocode.ProyectoBazar.model.Venta;

import java.util.List;

public class Mapper {
    //Cliente a ClienteDTO
    public static ClienteDTO toDTO(Cliente cliente) {
        if (cliente == null) {
            return null;
        }

        List<VentaDTO> ventaDTOs = cliente.getVentas().stream()
                .map(venta ->
                    VentaDTO.builder()
                            .codigo(venta.getCodigo_venta())
                            .fecha(venta.getFecha_venta())
                            .total(venta.getTotal())
                            .build()
                ).toList();

        return ClienteDTO.builder()
                .id_cliente(cliente.getId_cliente())
                .nombre(cliente.getNombre())
                .apellido(cliente.getApellido())
                .ventas(ventaDTOs)
                .build();
    }

    //Producto a ProductoDTO
    public static ProductoDTO toDTO(Producto producto) {
        if(producto == null) {
            return null;
        }
        return ProductoDTO.builder()
                .codigo_producto(producto.getCodigo_producto())
                .nombre(producto.getNombre())
                .marca(producto.getMarca())
                .costo(producto.getCosto())
                .cantidad(producto.getCantidad_disponible())
                .build();
    }

    //Venta a VentaDTO
    public static VentaDTO toDTO(Venta venta) {
        if (venta == null) {
            return null;
        }

        List<ProductoDTO> productoDTOS = venta.getListaProductos().stream()
                .map(producto ->
                        ProductoDTO.builder()
                                .codigo_producto(producto.getCodigo_producto())
                                .nombre(producto.getNombre())
                                .marca(producto.getMarca())
                                .costo(producto.getCosto())
                                .cantidad(producto.getCantidad_disponible())
                                .build()
                ).toList();

        ClienteDTO cliente = ClienteDTO.builder()
                .id_cliente(venta.getCliente().getId_cliente())
                .dni(venta.getCliente().getDni())
                .nombre(venta.getCliente().getNombre())
                .apellido(venta.getCliente().getApellido())
                .build();

        return VentaDTO.builder()
                .codigo(venta.getCodigo_venta())
                .fecha(venta.getFecha_venta())
                .total(venta.getTotal())
                .cliente(cliente)
                .build();
    }
}
