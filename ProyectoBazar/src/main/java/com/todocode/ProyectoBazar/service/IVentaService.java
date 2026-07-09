package com.todocode.ProyectoBazar.service;

import com.todocode.ProyectoBazar.dto.VentaDTO;

import java.util.List;

public interface IVentaService {
    public VentaDTO saveVenta(VentaDTO ventaDTO);
    public VentaDTO updateVenta(Long codigo_venta ,VentaDTO ventaDTO);
    public VentaDTO deleteVenta(Long codigo_venta);
    public VentaDTO findVenta(Long codigo_venta);
    public List<VentaDTO> findAllVentas();
}
