package com.uleam.tejenaproyecto.controlador;

import com.uleam.tejenaproyecto.interfaceservice.IClienteService;
import com.uleam.tejenaproyecto.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clienteList = clienteService.listar();
        return ResponseEntity.ok().body(clienteList);
    }

    @PostMapping
    public ResponseEntity<Cliente> saveCliente(@Validated @RequestBody Cliente cliente) {
        Cliente savedCliente = clienteService.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Cliente>> getClienteById(@PathVariable("id") Long id) {
        Optional<Cliente> cliente = clienteService.listarId(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok().body(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente cliente, @PathVariable("id") Long id) {
        Cliente updatedCliente = clienteService.update(id, cliente);
        return ResponseEntity.ok().body(updatedCliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClienteById(@PathVariable("id") Long id) {
        boolean deleted = clienteService.delete(id);
        if (deleted) {
            return ResponseEntity.ok().body("Cliente with id " + id + " has been successfully deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente with id " + id + " was not found.");
        }
    }
}
