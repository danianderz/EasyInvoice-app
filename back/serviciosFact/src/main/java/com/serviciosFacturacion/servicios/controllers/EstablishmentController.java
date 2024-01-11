package com.serviciosFacturacion.servicios.controllers;

import com.serviciosFacturacion.servicios.models.EstablishmentModel;
import com.serviciosFacturacion.servicios.models.IvaModel;
import com.serviciosFacturacion.servicios.services.EstablishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/establishment")
public class EstablishmentController {
    @Autowired
    private EstablishmentService establishmentService;

    @GetMapping("/get-establishment")
    public ArrayList<EstablishmentModel> getEstablishments(){
        return this.establishmentService.getEstablishmentS();
    }

    @PostMapping("/save-establishment")
    public EstablishmentModel saveEstablishment(@RequestBody EstablishmentModel  establishment){
        return this.establishmentService.saveEstablishment(establishment);
    }

    @GetMapping(path = "/{id}")
    public Optional<EstablishmentModel> getIvaById(@PathVariable("id") Long id_establecimiento ){
        return  this.establishmentService.getById(id_establecimiento);
    }

    @PutMapping(value = "/modif-establishment/{id}", path = "/modif-establishment/{id}")
    public EstablishmentModel updateEstablishmentById(@RequestBody EstablishmentModel request,@PathVariable("id") Long id_establecimiento){

        return this.establishmentService.updateById(request, id_establecimiento);
    }

    @DeleteMapping(value = "/delete-establishment/{id}", path = "/delete-establishment/{id}")
    public String deleteById(@PathVariable("id") Long id_establecimiento){
        boolean ok = this.establishmentService.deleteEstablis(id_establecimiento);
        if (ok){
            return  "Iva with id " + id_establecimiento + " delete";
        }else{
            return "Error, we have a problem";

        }
    }

}
