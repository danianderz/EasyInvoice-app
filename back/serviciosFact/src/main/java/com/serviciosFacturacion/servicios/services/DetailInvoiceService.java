package com.serviciosFacturacion.servicios.services;

import com.serviciosFacturacion.servicios.models.CustomerModel;
import com.serviciosFacturacion.servicios.models.DetailInvoiceModel;
import com.serviciosFacturacion.servicios.repositories.ICustomerRepository;
import com.serviciosFacturacion.servicios.repositories.IDetailInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class DetailInvoiceService {
    @Autowired
    IDetailInvoiceRepository iDetailInvoiceRepository;

    public ArrayList<DetailInvoiceModel> getDetailInvoices(){

        return (ArrayList<DetailInvoiceModel>) iDetailInvoiceRepository.findAll();
    }

    public DetailInvoiceModel saveDetailInvoice(DetailInvoiceModel detailInvoice){

        return iDetailInvoiceRepository.save(detailInvoice);
    }

    public Optional<DetailInvoiceModel> getById(Long id_detalle ){
        return  iDetailInvoiceRepository.findById(id_detalle);

    }

    @Transactional
    public DetailInvoiceModel updateById(DetailInvoiceModel request, Long id_detalle) {

        DetailInvoiceModel detail = iDetailInvoiceRepository.findById(id_detalle).orElse(null);
        if (detail != null) {

            detail.setId_comprob(request.getId_comprob());
            detail.setId_producto(request.getId_producto());
            detail.setDescuento(request.getDescuento());
            detail.setCantidad(request.getCantidad());
            detail.setPrecio_unitario(request.getPrecio_unitario());
            detail.setSubtotal_detalle(request.getSubtotal_detalle());

        }
        return detail;
    }

    public Boolean deleteDetailInvoce(Long id_detalle){
        try {
            iDetailInvoiceRepository.deleteById(id_detalle);
            return  true;
        }catch (Exception e){
            return  false;
        }
    }


}
