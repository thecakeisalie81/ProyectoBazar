package com.todocode.ProyectoBazar.controller;

import com.todocode.ProyectoBazar.dto.VentaDTO;
import com.todocode.ProyectoBazar.service.VentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    private final VentaService ventaService;
    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping("/crear")
    public ResponseEntity<VentaDTO> crearVenta(@RequestBody VentaDTO ventaDTO) {
        VentaDTO newVenta = ventaService.saveVenta(ventaDTO);
        return ResponseEntity.created(URI.create("/ventas/crear" + newVenta.getCodigo())).body(newVenta);
    }

    @GetMapping
    public ResponseEntity<List<VentaDTO>> obtenerVentas() {
        return ResponseEntity.ok().body(ventaService.findAllVentas());
    }

    @GetMapping("/{codigo_venta}")
    public ResponseEntity<VentaDTO> obtenerVenta(@PathVariable Long codigo_venta) {
        return ResponseEntity.ok().body(ventaService.findVenta(codigo_venta));
    }

    @DeleteMapping("/eliminar/{codigo_venta}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long codigo_venta) {
        ventaService.deleteVenta(codigo_venta);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/editar/{codigo_venta}")
    public ResponseEntity<VentaDTO> editarVenta(@PathVariable Long codigo_venta, @RequestBody VentaDTO ventaDTO) {
        return ResponseEntity.ok().body(ventaService.updateVenta(codigo_venta, ventaDTO));
    }
}
