package com.todocode.ProyectoBazar.dto;

import com.todocode.ProyectoBazar.model.Producto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class VentaDTO {
    Long codigo;
    LocalDate fecha;
    Double total;
    List<Producto> listaProductos;
    ClienteDTO cliente;
}
