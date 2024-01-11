package com.serviciosFacturacion.servicios.controllers;

import com.serviciosFacturacion.servicios.models.PromotionModel;
import com.serviciosFacturacion.servicios.services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/promotion")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;


    @GetMapping("/get-promotion")
    public ArrayList<PromotionModel> getPromotions() {
        return this.promotionService.getPromotions();
    }

    @PostMapping("/save-promotion")
    public PromotionModel savePromotion(@RequestBody PromotionModel promotion) {
        return this.promotionService.savePromotion(promotion);
    }


    @GetMapping(path = "/{id}")
    public Optional<PromotionModel> getPromotionById(@PathVariable("id") Long id_prom) {
        return this.promotionService.getById(id_prom);
    }

    @PutMapping(value = "/modif-promotion/{id}", path = "/modif-promotion/{id}")
    public PromotionModel updatePromotionById(@RequestBody PromotionModel request, @PathVariable("id") Long id_prom) {
        return this.promotionService.updateById(request, id_prom);

    }

    /**
    @DeleteMapping(value = "/delete-promotion/{id}", path = "/delete-promotion/{id}")
    public String deleteById(@PathVariable("id") Long id_prom) {
        boolean ok = this.promotionService.deletePromotion(id_prom);
        if (ok) {
            return "Promotion with id" + id_prom + "delete";
        } else {
            return "Error, we have a problem";

        }
    }
     */


    @DeleteMapping(value = "/delete-promotion/{id}", path = "/delete-promotion/{id}")
    public String deleteById(@PathVariable("id") Long id_prom) {
        PromotionModel promotion = promotionService.findById(id_prom);
        if (promotion != null) {
            promotion.setEst_prom((byte) 0);  // Establecer el campo est_prom a 0 en lugar de eliminar
            promotionService.updatePromotion(promotion);  // Llamar al método para actualizar la promoción en lugar de eliminarla
            return "Promotion with id " + id_prom + " set to inactive";
        } else {
            return "Promotion not found";
        }
    }

}

