package com.uleam.tejenaproyecto.interfaces;

import com.uleam.tejenaproyecto.modelo.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonalRepository extends JpaRepository<Personal, Long> {
}
