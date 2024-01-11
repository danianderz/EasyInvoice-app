package com.serviciosFacturacion.servicios.models;

import javax.persistence.*;


@Entity
@Table(name = "tienda")
public class StoreModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_tienda   ;

    @Column
    private String 	ruc ;
    @Column
    private Byte firma_electronica ;
    @Column
    private float lleva_contabilidad ;
    @Column
    private String 	codigo_eece ;

    public Long getId_tienda() {
        return id_tienda;
    }

    public void setId_tienda(Long id_tienda) {
        this.id_tienda = id_tienda;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public Byte getFirma_electronica() {
        return firma_electronica;
    }

    public void setFirma_electronica(Byte firma_electronica) {
        this.firma_electronica = firma_electronica;
    }

    public float getLleva_contabilidad() {
        return lleva_contabilidad;
    }

    public void setLleva_contabilidad(float lleva_contabilidad) {
        this.lleva_contabilidad = lleva_contabilidad;
    }

    public String getCodigo_eece() {
        return codigo_eece;
    }

    public void setCodigo_eece(String codigo_eece) {
        this.codigo_eece = codigo_eece;
    }
}
