package com.serviciosFacturacion.servicios.repositories;

import com.serviciosFacturacion.servicios.models.PromotionModel;
import com.serviciosFacturacion.servicios.models.StoreModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStoreRepository extends JpaRepository<StoreModel,Long > {

}