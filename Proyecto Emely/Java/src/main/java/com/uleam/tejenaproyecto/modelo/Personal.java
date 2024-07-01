package com.uleam.tejenaproyecto.modelo;

import jakarta.persistence.*;

@Entity         
@Table(name = "personal")
public class Personal {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "personal_id")
  private int personal_id;

  @Column(name = "nombre_personal")
  private String nombre_personal;

  @Column(name = "edad")
  private int edad;

  @Column(name = "direccion")
  private String direccion;

  @Column(name = "salario")
  private double salario;

  @ManyToOne
  @JoinColumn(name = "rol_id")
  private Rol rol;

  public Personal() {
  }

  public Personal(int personal_id, String nombre_personal, int edad, String direccion, double salario, Rol rol_id) {
    super();
    this.personal_id = personal_id;
    this.nombre_personal = nombre_personal;
    this.edad = edad;
    this.direccion = direccion;
    this.salario = salario;
    this.rol = rol_id;
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

  public Rol getRol_id() {
    return rol;
  }

  public void setRol_id(Rol rol_id) {
    this.rol = rol_id;
  }

  public int getPersonal_id() {
    return personal_id;
  }

  public void setPersonal_id(int personal_id) {
    this.personal_id = personal_id;
  }
}
