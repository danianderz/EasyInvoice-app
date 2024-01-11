package com.serviciosFacturacion.servicios.controllers;

import com.serviciosFacturacion.servicios.models.DetailInvoiceModel;
import com.serviciosFacturacion.servicios.models.UserModel;
import com.serviciosFacturacion.servicios.services.DetailInvoiceService;
import com.serviciosFacturacion.servicios.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/detailInvoice")
public class DetailInvoiceController {
    @Autowired
    private DetailInvoiceService detailInvoiceService;

    @GetMapping("/get-detail-invoice")
    public ArrayList<DetailInvoiceModel> getUsers(){
        return this.detailInvoiceService.getDetailInvoices();
    }

    @PostMapping("/save-detail-invoice")
    public  DetailInvoiceModel saveUser(@RequestBody DetailInvoiceModel  detail){
        return this.detailInvoiceService.saveDetailInvoice(detail);
    }

    @GetMapping(path = "/{id}")
    public Optional<DetailInvoiceModel> getDetailById(@PathVariable("id") Long id_detalle  ){
        return  this.detailInvoiceService.getById(id_detalle );
    }

    @PutMapping(value = "/modif-detail-invoice/{id}", path = "/modif-detail-invoice/{id}")
    public DetailInvoiceModel updateUserById(@RequestBody DetailInvoiceModel request,@PathVariable("id") Long id_detalle){
        return this.detailInvoiceService.updateById(request, id_detalle);
    }


    @DeleteMapping(value = "/delete-detail-invoice/{id}", path = "/delete-detail-invoice/{id}")
    public String deleteById(@PathVariable("id") Long id_detalle){
        boolean ok = this.detailInvoiceService.deleteDetailInvoce(id_detalle);
        if (ok){
            return  "Iva with id " + id_detalle + " delete";
        }else{
            return "Error, we have a problem";

        }
    }

}
