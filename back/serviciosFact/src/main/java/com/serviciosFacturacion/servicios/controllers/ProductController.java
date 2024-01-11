package com.serviciosFacturacion.servicios.controllers;

import com.serviciosFacturacion.servicios.models.CategoryModel;
import com.serviciosFacturacion.servicios.models.ProductModel;
import com.serviciosFacturacion.servicios.models.PromotionModel;
import com.serviciosFacturacion.servicios.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/get-products")

    public ArrayList<ProductModel> getProducts() {
        return this.productService.getProducts();
    }
    @PostMapping("/save-product")
    public ProductModel saveProduct(@RequestBody ProductModel product) {
        System.out.println(product.getEst_producto());
        return this.productService.saveProduct(product);
    }

    @GetMapping(path = "/{id}")
    public Optional<ProductModel> getProductById(@PathVariable("id") Long id_producto ){
        return  this.productService.getById(id_producto);
    }
    //
    @PutMapping(value = "/modif-product/{id}", path = "/modif-product/{id}")
    public ProductModel updateProductById(@RequestBody ProductModel request,@PathVariable("id") Long id_producto){
        return this.productService.updateById(request, id_producto);

    }

    //aaa
    @DeleteMapping(value = "/delete-product/{id}", path = "/delete-product/{id}")
    //@DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id_producto){
        boolean ok = this.productService.deleteProduct(id_producto);
        if (ok){
            return  "Iva with id" + id_producto + " delete";
        }else{
            return "Error, we have a problem";

        }
    }

}
