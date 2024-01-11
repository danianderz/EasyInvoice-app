    package com.serviciosFacturacion.servicios.models;

    import javax.persistence.*;
    import java.util.Date;

    @Entity
    @Table(name = "factura")
    public class InvoiceModel {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        //@Column(columnDefinition = "bigint default 0")
        private Long id_comprob   ;

        @Column
        private int id_tip_pago ;
        @Column
        private String  num_comprob;
        @Column
        private Date fecha_emision;
        @Column
        private Byte estado_factura;
        @Column
        private float subtotal;
        @Column
        private float iva;
        @Column
        private float total;
        @Column
        private int id_usuario ;
        @Column
        private int id_cliente ;
        @Column
        private  int tipo_comprobante;

        public Long getId_comprob() {
            return id_comprob;
        }

        public void setId_comprob(Long id_comprob) {
            this.id_comprob = id_comprob;
        }

        public int getId_tip_pago() {
            return id_tip_pago;
        }

        public void setId_tip_pago(int id_tip_pago) {
            this.id_tip_pago = id_tip_pago;
        }

        public String getNum_comprob() {
            return num_comprob;
        }

        public void setNum_comprob(String num_comprob) {
            this.num_comprob = num_comprob;
        }

        public Date getFecha_emision() {
            return fecha_emision;
        }

        public void setFecha_emision(Date fecha_emision) {
            this.fecha_emision = fecha_emision;
        }

        public Byte getEstado_factura() {
            return estado_factura;
        }

        public void setEstado_factura(Byte estado_factura) {
            this.estado_factura = estado_factura;
        }

        public float getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(float subtotal) {
            this.subtotal = subtotal;
        }

        public float getIva() {
            return iva;
        }

        public void setIva(float iva) {
            this.iva = iva;
        }

        public float getTotal() {
            return total;
        }

        public void setTotal(float total) {
            this.total = total;
        }

        public int getId_usuario() {
            return id_usuario;
        }

        public void setId_usuario(int id_usuario) {
            this.id_usuario = id_usuario;
        }

        public int getId_cliente() {
            return id_cliente;
        }

        public void setId_cliente(int id_cliente) {
            this.id_cliente = id_cliente;
        }

        public int getTipo_comprobante() {
            return tipo_comprobante;
        }

        public void setTipo_comprobante(int tipo_comprobante) {
            this.tipo_comprobante = tipo_comprobante;
        }
    }
