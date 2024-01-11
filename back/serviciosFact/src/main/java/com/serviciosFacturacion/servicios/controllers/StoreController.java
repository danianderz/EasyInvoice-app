package com.serviciosFacturacion.servicios.controllers;

import com.serviciosFacturacion.servicios.models.CategoryModel;
import com.serviciosFacturacion.servicios.models.StoreModel;
import com.serviciosFacturacion.servicios.services.CategoryService;
import com.serviciosFacturacion.servicios.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @GetMapping("/get-stores")
    public ArrayList<StoreModel> getCategories(){
        return this.storeService.getStores();
    }
}
