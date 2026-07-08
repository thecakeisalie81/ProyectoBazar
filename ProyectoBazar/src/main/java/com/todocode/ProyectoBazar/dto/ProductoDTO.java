package com.todocode.ProyectoBazar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoDTO {
    Long codigo_producto;
    String nombre;
    String marca;
    Double costo;
    Double cantidad;
}
