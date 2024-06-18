package com.uleam.tejenaproyecto.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "personal")
public class Personal {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "personal_id")
  private Long personal_id;

  @Column(name = "nombre_personal")
  private String nombre_personal;

  @Column(name = "edad")
  private int edad;

  @Column(name = "direccion")
  private String direccion;

  @Column(name = "salario")
  private double salario;

  @Column(name = "rol_id")
  private Long rol_id;


  public Personal() {
  }

  public Personal(Long personal_id, String nombre_personal, int edad, String direccion, double salario, Long rol) {
    super();
    this.personal_id = personal_id;
    this.nombre_personal = nombre_personal;
    this.edad = edad;
    this.direccion = direccion;
    this.salario = salario;
    this.rol_id = rol;
  }

  public String getNombre_personal() {
    return nombre_personal;
  }

  public void setNombre_personal(String nombre_personal) {
    this.nombre_personal = nombre_personal;
  }

  public int getEdad() {
    return edad;
  }

  public void setEdad(int edad) {
    this.edad = edad;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public double getSalario() {
    return salario;
  }

  public void setSalario(double salario) {
    this.salario = salario;
  }

  public Long getRol() {
    return rol_id;
  }

  public void setRol(Long rol) {
    this.rol_id = rol;
  }

  public Long getPersonal_id() {
    return personal_id;
  }

  public void setPersonal_id(Long personal_id) {
    this.personal_id = personal_id;
  }
}
