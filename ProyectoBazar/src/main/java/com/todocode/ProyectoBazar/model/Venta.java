package com.todocode.ProyectoBazar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo_venta;
    private LocalDate fecha_venta;
    private Double total;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "codigo_producto")
    private List<Producto> listaProductos;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
}
