package com.todocode.ProyectoBazar.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClienteDTO {
    Long id_cliente;
    String nombre;
    String apellido;
    String dni;
    List<VentaDTO> ventas;
}
