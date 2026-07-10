package com.todocode.ProyectoBazar.controller;

import com.todocode.ProyectoBazar.dto.ClienteDTO;
import com.todocode.ProyectoBazar.service.IClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final IClienteService clienteService;

    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/crear")
    public ResponseEntity<ClienteDTO> crearCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteDTO1 = clienteService.saveCliente(clienteDTO);
        return ResponseEntity.created(URI.create("/clientes/crear"+clienteDTO1.getId_cliente())).body(clienteDTO1);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAllClientes() {
        return ResponseEntity.ok(clienteService.findAllClientes());
    }

    @GetMapping("/{id_cliente}")
    public ResponseEntity<ClienteDTO> findClienteById(@PathVariable Long id_cliente) {
        return ResponseEntity.ok(clienteService.findClienteById(id_cliente));
    }

    @DeleteMapping("/eliminar/{id_cliente}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id_cliente) {
        clienteService.deleteClienteById(id_cliente);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/editar/{id_cliente}")
    public ResponseEntity<ClienteDTO>  editarCliente(@PathVariable Long id_cliente, @RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteService.updateClienteById(id_cliente, clienteDTO));
    }
}
