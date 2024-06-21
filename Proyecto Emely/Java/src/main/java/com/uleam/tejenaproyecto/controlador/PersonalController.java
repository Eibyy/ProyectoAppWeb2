package com.uleam.tejenaproyecto.controlador;

import com.uleam.tejenaproyecto.interfaceservice.IPersonalService;
import com.uleam.tejenaproyecto.modelo.Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/personal")
public class PersonalController {

    @Autowired
    private IPersonalService personalService;

    @GetMapping
    public ResponseEntity<List<Personal>> getAllPersonal() {
        List<Personal> personalList = personalService.listar();
        return ResponseEntity.ok().body(personalList);
    }

    @PostMapping
    public ResponseEntity<Personal> savePersonal(@Validated @RequestBody Personal personal) {
        Personal savedPersonal = personalService.save(personal);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPersonal);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Personal>> getPersonalById(@PathVariable("id") Long id) {
        Optional<Personal> personal = personalService.listarId(id);
        if (personal.isPresent()) {
            return ResponseEntity.ok().body(personal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personal> updatePersonal(@RequestBody Personal personal, @PathVariable("id") Long id) {
        Personal updatedPersonal = personalService.update(id, personal);
        return ResponseEntity.ok().body(updatedPersonal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersonalById(@PathVariable("id") Long id) {
        boolean deleted = personalService.delete(id);
        if (deleted) {
            return ResponseEntity.ok().body("User with id " + id + " has been successfully deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id " + id + " was not found.");
        }
    }
}
