package com.serviciosFacturacion.servicios.models;

import javax.persistence.*;

@Entity
@Table(name = "establecimiento")
public class EstablishmentModel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_establecimiento  ;

    @Column
    private int id_tienda_per;
    @Column
    private  String nombre;
    @Column
    private String telefono;
    @Column
    private String direccion;
    @Column
    private String email;
    @Column
    private Byte est_establecimiento;

    public Long getId_establecimiento() {
        return id_establecimiento;
    }

    public void setId_establecimiento(Long id_establecimiento) {
        this.id_establecimiento = id_establecimiento;
    }

    public int getId_tienda_per() {
        return id_tienda_per;
    }

    public void setId_tienda_per(int id_tienda_per) {
        this.id_tienda_per = id_tienda_per;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Byte getEst_establecimiento() {
        return est_establecimiento;
    }

    public void setEst_establecimiento(Byte est_establecimiento) {
        this.est_establecimiento = est_establecimiento;
    }
}
