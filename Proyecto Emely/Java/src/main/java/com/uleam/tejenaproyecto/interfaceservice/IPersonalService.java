package com.uleam.tejenaproyecto.interfaceservice;

import com.uleam.tejenaproyecto.modelo.Personal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface IPersonalService {

    public List<Personal> listar();
    public Optional<Personal>listarId(Long id);
    public Personal save(Personal p);
    public Personal update(Long id, Personal newUser);
    public boolean delete(Long userId);


}
