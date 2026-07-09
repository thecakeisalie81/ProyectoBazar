package com.todocode.ProyectoBazar.service;

import com.todocode.ProyectoBazar.dto.VentaDTO;
import com.todocode.ProyectoBazar.exception.NotFoundException;
import com.todocode.ProyectoBazar.mapper.Mapper;
import com.todocode.ProyectoBazar.model.Cliente;
import com.todocode.ProyectoBazar.model.Producto;
import com.todocode.ProyectoBazar.model.Venta;
import com.todocode.ProyectoBazar.repository.IClienteRepository;
import com.todocode.ProyectoBazar.repository.IProductoRepository;
import com.todocode.ProyectoBazar.repository.IVentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService implements IVentaService{
    private final IVentaRepository ventaRepository;
    private final IClienteRepository clienteRepository;
    private final IProductoRepository productoRepository;

    public VentaService(IVentaRepository ventaRepository, IClienteRepository clienteRepository, IProductoRepository productoRepository) {
        this.ventaRepository = ventaRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public VentaDTO saveVenta(VentaDTO ventaDTO) {
        Cliente cliente = clienteRepository.findById(ventaDTO.getCliente().getId_cliente())
                .orElseThrow(()-> new NotFoundException("Cliente no encontrado"));
        List<Producto> productoList = ventaDTO.getListaProductos().stream()
                .map(producto -> productoRepository.findById(producto.getCodigo_producto())
                        .orElseThrow(()-> new NotFoundException("Producto no encontrado")))
                .toList();
        Venta venta = new Venta();
        venta.setFecha_venta(ventaDTO.getFecha());
        venta.setCliente(cliente);
        venta.setTotal(ventaDTO.getTotal());
        venta.setListaProductos(productoList);
        return Mapper.toDTO(ventaRepository.save(venta));
    }

    @Override
    public VentaDTO updateVenta(Long codigo_venta, VentaDTO ventaDTO) {
        Venta venta = ventaRepository.findById(codigo_venta)
                .orElseThrow(()-> new NotFoundException("Venta no encontrado"));
        Cliente cliente = clienteRepository.findById(ventaDTO.getCliente().getId_cliente())
                .orElseThrow(()-> new NotFoundException("Cliente no encontrado"));
        List<Producto> productoList = ventaDTO.getListaProductos().stream()
                .map(producto -> productoRepository.findById(producto.getCodigo_producto())
                        .orElseThrow(()-> new NotFoundException("Producto no encontrado")))
                .toList();

        venta.setFecha_venta(ventaDTO.getFecha());
        venta.setTotal(ventaDTO.getTotal());
        venta.setListaProductos(productoList);
        venta.setTotal(ventaDTO.getTotal());
        venta.setCliente(cliente);

        return Mapper.toDTO(ventaRepository.save(venta));
    }

    @Override
    public VentaDTO deleteVenta(Long codigo_venta) {
        Venta venta = ventaRepository.findById(codigo_venta)
                .orElseThrow(()-> new NotFoundException("Venta no encontrado"));
        ventaRepository.delete(venta);
        return Mapper.toDTO(venta);
    }

    @Override
    public VentaDTO findVenta(Long codigo_venta) {
        return ventaRepository.findById(codigo_venta).map(Mapper::toDTO)
                .orElseThrow(()->new NotFoundException("Venta no encontrada"));
    }

    @Override
    public List<VentaDTO> findAllVentas() {
        return ventaRepository.findAll().stream().map(Mapper::toDTO).toList();
    }
}
