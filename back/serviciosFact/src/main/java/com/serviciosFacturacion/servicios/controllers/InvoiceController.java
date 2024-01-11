package com.serviciosFacturacion.servicios.controllers;

import com.serviciosFacturacion.servicios.models.EstablishmentModel;
import com.serviciosFacturacion.servicios.models.InvoiceModel;
import com.serviciosFacturacion.servicios.services.EstablishmentService;
import com.serviciosFacturacion.servicios.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/get-invoice")
    public ArrayList<InvoiceModel> getInvoices(){
        return this.invoiceService.getInvoices();
    }

    @PostMapping("/save-invoice")
    public InvoiceModel saveInvoice(@RequestBody InvoiceModel  invoice){
        System.out.println(invoice.getNum_comprob());
        return this.invoiceService.saveInvoice(invoice);
    }

    @GetMapping(path = "/{id}")
    public Optional<InvoiceModel> getInvoiceById(@PathVariable("id") Long id_comprob ){
        return  this.invoiceService.getById(id_comprob);
    }

    @PutMapping(value = "/modif-invoice/{id}", path = "/modif-invoice/{id}")
    public InvoiceModel updateInvoiceById(@RequestBody InvoiceModel request,@PathVariable("id") Long id_comprob){

        return this.invoiceService.updateById(request, id_comprob);
    }

    @DeleteMapping(value = "/delete-invoice/{id}", path = "/delete-invoice/{id}")
    public String deleteById(@PathVariable("id") Long id_comprob){
        boolean ok = this.invoiceService.deleteInvoice(id_comprob);
        if (ok){
            return  "Iva with id " + id_comprob + " delete";
        }else{
            return "Error, we have a problem";

        }
    }

}
