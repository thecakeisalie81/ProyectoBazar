package com.todocode.ProyectoBazar.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductoDTO {
    Long codigo_producto;
    String nombre;
    String marca;
    Double costo;
    Double cantidad;
}
