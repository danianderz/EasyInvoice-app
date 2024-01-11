package com.serviciosFacturacion.servicios.repositories;

import com.serviciosFacturacion.servicios.models.InvoiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInvoiceRepository extends JpaRepository<InvoiceModel,Long > {
}
