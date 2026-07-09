package com.todocode.ProyectoBazar.service;

import com.todocode.ProyectoBazar.dto.ClienteDTO;
import com.todocode.ProyectoBazar.exception.NotFoundException;
import com.todocode.ProyectoBazar.mapper.Mapper;
import com.todocode.ProyectoBazar.model.Cliente;
import com.todocode.ProyectoBazar.repository.IClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService{

    private final IClienteRepository clienteRepository;

    public ClienteService(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteDTO saveCliente(ClienteDTO clienteDTO) {
        Cliente cliente = Cliente.builder()
                .nombre(clienteDTO.getNombre())
                .apellido(clienteDTO.getApellido())
                .dni(clienteDTO.getDni())
                .build();
        return Mapper.toDTO(clienteRepository.save(cliente));
    }

    @Override
    public List<ClienteDTO> findAllClientes() {
        return clienteRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ClienteDTO findClienteById(Long id) {
        return clienteRepository.findById(id).map(Mapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
    }

    @Override
    public ClienteDTO deleteClienteById(Long id) {
        Cliente cliente =  clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
        clienteRepository.delete(cliente);
        return Mapper.toDTO(cliente);
    }

    @Override
    public ClienteDTO updateClienteById(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));

        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setDni(clienteDTO.getDni());
        return Mapper.toDTO(clienteRepository.save(cliente));
    }
}
