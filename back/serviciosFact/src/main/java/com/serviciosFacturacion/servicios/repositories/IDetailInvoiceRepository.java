package com.serviciosFacturacion.servicios.repositories;

import com.serviciosFacturacion.servicios.models.DetailInvoiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDetailInvoiceRepository  extends JpaRepository<DetailInvoiceModel,Long > {
}
