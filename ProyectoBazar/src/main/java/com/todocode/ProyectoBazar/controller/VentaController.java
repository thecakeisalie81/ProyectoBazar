package com.todocode.ProyectoBazar.controller;

import com.todocode.ProyectoBazar.dto.ProductoDTO;
import com.todocode.ProyectoBazar.dto.VentaDTO;
import com.todocode.ProyectoBazar.service.VentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/productos/{codigo_venta}")
    public ResponseEntity<List<ProductoDTO>> obtenerProductos(@PathVariable Long codigo_venta) {
        return ResponseEntity.ok(ventaService.findVenta(codigo_venta).getListaProductos());
    }

    @GetMapping("/{fecha_venta}")
    public ResponseEntity<Map<String, Double>> montoYCantidadPorFecha(@PathVariable LocalDate fecha_venta) {
        List<VentaDTO> listaVentas = ventaService.findAllVentas().stream()
                .filter(ventaDTO -> ventaDTO.getFecha().equals(fecha_venta)).toList();
        Double montoTotal = listaVentas.stream().mapToDouble(VentaDTO::getTotal).sum();
        Double cantidadTotal = (double) listaVentas.size();
        Map<String, Double> response = new HashMap<>();
        response.put("montoTotal: ", montoTotal);
        response.put("ventasTotales: ", cantidadTotal);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/mayor_venta")
    public ResponseEntity<VentaDTO> obtenerMayorVenta() {
        List<VentaDTO> listaVentas = ventaService.findAllVentas();
        VentaDTO mayorVenta = listaVentas.stream()
                .max(Comparator.comparing(VentaDTO::getTotal)).orElseThrow();
        return ResponseEntity.ok().body(mayorVenta);
    }
}
