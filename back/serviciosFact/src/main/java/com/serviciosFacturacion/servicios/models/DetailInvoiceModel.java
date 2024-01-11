package com.serviciosFacturacion.servicios.models;

import javax.persistence.*;

@Entity
@Table(name = "detalle_factura")
public class DetailInvoiceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(columnDefinition = "bigint default 0")
    private Long id_detalle ;

    @Column
    private int id_comprob;
    @Column
    private int id_producto;
    @Column
    private double descuento;
    @Column
    private int cantidad;
    @Column
    private double precio_unitario;
    @Column
    private double subtotal_detalle;

    public Long getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(Long id_detalle) {
        this.id_detalle = id_detalle;
    }

    public int getId_comprob() {
        return id_comprob;
    }

    public void setId_comprob(int id_comprob) {
        this.id_comprob = id_comprob;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public double getSubtotal_detalle() {
        return subtotal_detalle;
    }

    public void setSubtotal_detalle(double subtotal_detalle) {
        this.subtotal_detalle = subtotal_detalle;
    }
}
