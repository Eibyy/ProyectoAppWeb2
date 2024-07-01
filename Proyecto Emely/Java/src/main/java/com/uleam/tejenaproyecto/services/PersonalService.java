package com.uleam.tejenaproyecto.services;

import com.uleam.tejenaproyecto.interfaces.IPersonal;
import com.uleam.tejenaproyecto.interfaceservice.IPersonalService;
import com.uleam.tejenaproyecto.modelo.Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalService implements IPersonalService {

    @Autowired
    private IPersonal ipersonal;

    @Override
    public List<Personal> listar() {
        return (List<Personal>)ipersonal.findAll();
    }

    @Override
    public Optional<Personal> listarId(int id) {
        return ipersonal.findById(id);
    }

    @Override
    public int save(Personal p) {
        int res=0;
        Personal persona = ipersonal.save(p);
        if (!persona.equals(null)){
            return res;
        }
        return res;
    }

    @Override
    public void delete(int id) {
    ipersonal.deleteById(id);
    }
}
