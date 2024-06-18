package com.uleam.tejenaproyecto.controlador;

import com.uleam.tejenaproyecto.interfaceservice.IRolService;
import com.uleam.tejenaproyecto.modelo.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private IRolService rolService;

    @GetMapping
    public ResponseEntity<List<Rol>> getAllRoles() {
        List<Rol> roles = rolService.listar();
        return ResponseEntity.ok().body(roles);
    }

    @PostMapping
    public ResponseEntity<Rol> saveRol(@RequestBody Rol rol) {
        Rol savedRol = rolService.save(rol);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRol);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> getRolById(@PathVariable("id") Long id) {
        Optional<Rol> rol = rolService.listarId(id);
        return rol.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> updateRol(@RequestBody Rol rol, @PathVariable("id") Long id) {
        Rol updatedRol = rolService.update(id, rol);
        return ResponseEntity.ok().body(updatedRol);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRolById(@PathVariable("id") Long id) {
        boolean deleted = rolService.delete(id);
        if (deleted) {
            return ResponseEntity.ok().body("Role with id " + id + " has been successfully deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role with id " + id + " was not found.");
        }
    }
}
