package com.serviciosFacturacion.servicios.services;

import com.serviciosFacturacion.servicios.models.PromotionModel;
import com.serviciosFacturacion.servicios.models.StoreModel;
import com.serviciosFacturacion.servicios.repositories.IStoreRepository;
import com.serviciosFacturacion.servicios.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StoreService {

    @Autowired
    IStoreRepository storeRepository;

    public ArrayList<StoreModel> getStores(){
        return (ArrayList<StoreModel>) storeRepository.findAll();
    }
}
