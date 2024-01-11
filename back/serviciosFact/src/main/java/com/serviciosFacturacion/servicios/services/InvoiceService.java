package com.serviciosFacturacion.servicios.services;

import com.serviciosFacturacion.servicios.models.EstablishmentModel;
import com.serviciosFacturacion.servicios.models.InvoiceModel;
import com.serviciosFacturacion.servicios.repositories.IEstablishment;
import com.serviciosFacturacion.servicios.repositories.IInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired
    IInvoiceRepository iInvoiceRepository;

    public ArrayList<InvoiceModel> getInvoices() {
        return (ArrayList<InvoiceModel>) iInvoiceRepository.findAll();
    }

    public InvoiceModel saveInvoice(InvoiceModel invoice){

        return iInvoiceRepository.save(invoice);
    }

    public Optional<InvoiceModel> getById(Long id_comprob   ){
        return  iInvoiceRepository.findById(id_comprob);

    }


    @Transactional
    public InvoiceModel updateById(InvoiceModel request, Long id_comprob) {

        InvoiceModel invoice = iInvoiceRepository.findById(id_comprob).orElse(null);
        if (invoice != null) {
            invoice.setId_tip_pago(request.getId_tip_pago());
            invoice.setNum_comprob(request.getNum_comprob());
            invoice.setFecha_emision(request.getFecha_emision());
            invoice.setSubtotal(request.getSubtotal());
            invoice.setIva(request.getIva());
            invoice.setTotal(request.getTotal());
            invoice.setId_usuario(request.getId_usuario());
            invoice.setId_cliente(request.getId_cliente());
            invoice.setTipo_comprobante(request.getTipo_comprobante());
        }
        return invoice;
    }




    public Boolean deleteInvoice(Long id_comprob){
        try {
            iInvoiceRepository.deleteById(id_comprob);
            return  true;
        }catch (Exception e){
            return  false;
        }
    }


}
