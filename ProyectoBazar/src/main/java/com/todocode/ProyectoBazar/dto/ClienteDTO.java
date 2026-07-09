package com.todocode.ProyectoBazar.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ClienteDTO {
    Long id_cliente;
    String nombre;
    String apellido;
    String dni;
    List<VentaDTO> ventas;
}
