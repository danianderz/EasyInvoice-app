package com.serviciosFacturacion.servicios.services;

import com.serviciosFacturacion.servicios.models.CategoryModel;
import com.serviciosFacturacion.servicios.models.IvaModel;
import com.serviciosFacturacion.servicios.models.ProductModel;
import com.serviciosFacturacion.servicios.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    IProductRepository productRepository;

    public ArrayList<ProductModel> getProducts(){
        return (ArrayList<ProductModel>) productRepository.findAll();
    }
    public ProductModel saveProduct(ProductModel product){
        return productRepository.save(product);
    }

    public Optional<ProductModel> getById(Long id_producto ){
        return  productRepository.findById(	id_producto  );

    }


    @Transactional
    public ProductModel updateById(ProductModel request, Long id_producto) {

        ProductModel product = productRepository.findById(id_producto).orElse(null);
        if (product != null) {
            product.setId_promocion(request.getId_promocion());
            product.setId_categ(request.getId_categ());
            product.setId_categ_iva(request.getId_categ_iva());
            product.setNom_producto(request.getNom_producto());
            product.setDescrip_prod(request.getDescrip_prod());
            product.setPrecio(request.getPrecio());
            product.setUnidades_dispon(request.getUnidades_dispon());
            product.setCodigo_barras(request.getCodigo_barras());
            product.setEst_producto(request.getEst_producto());
        }
        return product;
    }

    public Boolean deleteProduct(Long id_producto){
        try {
            productRepository.deleteById(id_producto);
            return  true;
        }catch (Exception e){
            return  false;
        }
    }



}
