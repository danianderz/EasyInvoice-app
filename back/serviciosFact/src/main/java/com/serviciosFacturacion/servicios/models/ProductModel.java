package com.serviciosFacturacion.servicios.models;

import javax.persistence.*;

@Entity
@Table(name = "producto")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_producto ;

    @Column
    private int id_promocion;
    @Column
    private int id_categ;
    @Column
    private int id_categ_iva;
    @Column
    private String nom_producto;
    @Column
    private String descrip_prod;
    @Column
    private double precio;
    @Column
    private int unidades_dispon;
    @Column
    private String codigo_barras;
    @Column
    private  Byte est_producto;

    public Byte getEst_producto() {
        return est_producto;
    }

    public void setEst_producto(Byte est_producto) {
        this.est_producto = est_producto;
    }

    public Long getId_producto() {
        return id_producto;
    }

    public void setId_producto(Long id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_promocion() {
        return id_promocion;
    }

    public void setId_promocion(int id_promocion) {
        this.id_promocion = id_promocion;
    }

    public int getId_categ() {
        return id_categ;
    }

    public void setId_categ(int id_categ) {
        this.id_categ = id_categ;
    }

    public int getId_categ_iva() {
        return id_categ_iva;
    }

    public void setId_categ_iva(int id_categ_iva) {
        this.id_categ_iva = id_categ_iva;
    }

    public String getNom_producto() {
        return nom_producto;
    }

    public void setNom_producto(String nom_producto) {
        this.nom_producto = nom_producto;
    }

    public String getDescrip_prod() {
        return descrip_prod;
    }

    public void setDescrip_prod(String descrip_prod) {
        this.descrip_prod = descrip_prod;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getUnidades_dispon() {
        return unidades_dispon;
    }

    public void setUnidades_dispon(int unidades_dispon) {
        this.unidades_dispon = unidades_dispon;
    }

    public String getCodigo_barras() {
        return codigo_barras;
    }

    public void setCodigo_barras(String codigo_barras) {
        this.codigo_barras = codigo_barras;
    }
}
