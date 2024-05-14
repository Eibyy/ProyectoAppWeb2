package com.uleam.tejenaproyecto.controlador;

import com.uleam.tejenaproyecto.interfaceservice.IPersonalService;
import com.uleam.tejenaproyecto.modelo.Personal;
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
public class PersonalController {

    @Autowired
    private IPersonalService services;

    @GetMapping("/personal/listado")
    public String listar(Model model) {
        List<Personal>personal= services.listar();
        model.addAttribute("personal", personal);
        return "pages/personal_listado";
    }
    @GetMapping("/personal/nuevo")
    public String agregar(Model model){
        model.addAttribute("personal", new Personal());
        return "pages/personal_formulario";
    }

    @PostMapping("/personal/save")
    public String save(@Validated Personal p, Model model){
        services.save(p);
        return "redirect:/personal/listado";
    }
    @GetMapping("/personal/editar/{id}")
    public String editar(@PathVariable int id, Model model){
        Optional<Personal> personales =services.listarId(id);
        model.addAttribute("personal", personales);
        return "pages/personal_formulario";
    }
    @GetMapping("/personal/eliminar/{id}")
    public String eliminar(Model model, @PathVariable int id){
        services.delete(id);
        return "redirect:/personal/listado";
    }

}
