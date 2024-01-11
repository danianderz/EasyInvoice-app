package com.serviciosFacturacion.servicios.repositories;


import com.serviciosFacturacion.servicios.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<ProductModel,Long > {
}
