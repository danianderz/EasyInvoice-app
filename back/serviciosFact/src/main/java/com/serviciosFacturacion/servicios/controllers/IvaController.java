package com.serviciosFacturacion.servicios.controllers;

import com.serviciosFacturacion.servicios.models.CategoryModel;
import com.serviciosFacturacion.servicios.models.IvaModel;
import com.serviciosFacturacion.servicios.services.IvaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/iva")
public class IvaController {
    @Autowired
    private IvaService ivaService;
    @GetMapping("/get-iva")
    public ArrayList<IvaModel> getIvas(){
        return this.ivaService.getIvas();
    }

    @PostMapping("/save-iva")
    public  IvaModel saveIva(@RequestBody IvaModel  iva){
        return this.ivaService.saveIva(iva);
    }




    @GetMapping(path = "/{id}")
    public Optional<IvaModel> getIvaById(@PathVariable("id") Long id_categ_iva ){
        return  this.ivaService.getById(id_categ_iva);
    }

    @PutMapping(value = "/modif-iva/{id}", path = "/modif-iva/{id}")
    public IvaModel updateIvaById(@RequestBody IvaModel request,@PathVariable("id") Long id_categ_iva){
        return this.ivaService.updateById(request, id_categ_iva);

    }


    /**
    @DeleteMapping(value = "/delete-iva/{id}", path = "/delete-iva/{id}")
    public String deleteById(@PathVariable("id") Long id_categ_iva){
        boolean ok = this.ivaService.deleteIva(id_categ_iva);
        if (ok){
            return  "Iva with id" + id_categ_iva + "delete";
        }else{
            return "Error, we have a problem";

        }
    }
     */
    @DeleteMapping(value = "/delete-iva/{id}", path = "/delete-iva/{id}")
    public String deleteById(@PathVariable("id") Long id_categ_iva ) {
        IvaModel iva = ivaService.findById(id_categ_iva );
        if (iva != null) {
            iva.setEst_categ_iva((byte) 0);  // Establecer el campo est_prom a 0 en lugar de eliminar
            ivaService.updateIva(iva);  // Llamar al método para actualizar la promoción en lugar de eliminarla
            return "IVA  CATEGORY  with id " + id_categ_iva  + " set to inactive";
        } else {
            return "IVA  CATEGORY  not found";
        }
    }


}

