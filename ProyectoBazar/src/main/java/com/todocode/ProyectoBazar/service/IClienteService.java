package com.todocode.ProyectoBazar.service;


import com.todocode.ProyectoBazar.dto.ClienteDTO;

import java.util.List;

public interface IClienteService {
    public ClienteDTO saveCliente(ClienteDTO clienteDTO);
    public List<ClienteDTO> findAllClientes();
    public ClienteDTO findClienteById(Long id);
    public ClienteDTO deleteClienteById(Long id);
    public ClienteDTO updateClienteById(Long id, ClienteDTO clienteDTO);
}
