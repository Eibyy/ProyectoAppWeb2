package com.uleam.tejenaproyecto.controlador;

import com.uleam.tejenaproyecto.interfaceservice.IPersonalService;
import com.uleam.tejenaproyecto.interfaceservice.IRolService;
import com.uleam.tejenaproyecto.modelo.Personal;
import com.uleam.tejenaproyecto.modelo.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class RolController {

    @Autowired
    private IRolService services;

    @GetMapping("/rol/listado")
    public String listar(Model model) {
        List<Rol> rol= services.listar();
        model.addAttribute("rol", rol);
        return "pages/rol_listado";
    }
    @GetMapping("/rol/nuevo")
    public String agregar(Model model){
        model.addAttribute("rol", new Rol());
        return "pages/rol_formulario";
    }

    @PostMapping("/rol/save")
    public String save(@Validated Rol p, Model model){
        services.save(p);
        return "redirect:/rol/listado";
    }
    @GetMapping("/rol/editar/{id}")
    public String editar(@PathVariable int id, Model model){
        Optional<Rol> roles =services.listarId(id);
        model.addAttribute("rol", roles);
        return "pages/rol_formulario";
    }
    @GetMapping("/rol/eliminar/{id}")
    public String eliminar(Model model, @PathVariable int id){
        services.delete(id);
        return "redirect:/rol/listado";
    }

}
