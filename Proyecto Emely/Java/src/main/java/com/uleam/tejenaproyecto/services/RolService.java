package com.uleam.tejenaproyecto.services;

import com.uleam.tejenaproyecto.interfaces.IRol;
import com.uleam.tejenaproyecto.interfaceservice.IRolService;
import com.uleam.tejenaproyecto.modelo.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService implements IRolService {

    @Autowired
    private IRol irol;

    @Override
    public List<Rol> listar() {
        return (List<Rol>)irol.findAll();
    }

    @Override
    public Optional<Rol> listarId(int id) {
        return irol.findById(id);
    }

    @Override
    public int save(Rol p) {
        int res=0;
        Rol rol = irol.save(p);
        if (!rol.equals(null)){
            return res;
        }
        return res;
    }

    @Override
    public void delete(int id) {
        irol.deleteById(id);
    }
}
