package com.serviciosFacturacion.servicios.controllers;

import com.serviciosFacturacion.servicios.models.CategoryModel;
import com.serviciosFacturacion.servicios.models.PromotionModel;
import com.serviciosFacturacion.servicios.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {
    // Metodos Servicio CRUD
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/get-category")
    public ArrayList<CategoryModel> getCategories(){
        return this.categoryService.getCategories();
    }

    @PostMapping("/save-category")
    public  CategoryModel saveCategory(@RequestBody CategoryModel  category){
        return this.categoryService.saveCategory(category);
    }

    @GetMapping(path = "/{id}")
    public Optional<CategoryModel> getIvaById(@PathVariable("id") Long id_categ ){
        return  this.categoryService.getById(id_categ);
    }
    //
    @PutMapping(value = "/modif-category/{id}", path = "/modif-category/{id}")
    public CategoryModel updateCategoryById(@RequestBody CategoryModel request,@PathVariable("id") Long id_categ){
        return this.categoryService.updateById(request, id_categ);

    }

    /**
    @DeleteMapping(value = "/delete-category/{id}", path = "/delete-category/{id}")
    //@DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id_categ){
        boolean ok = this.categoryService.deleteCategory(id_categ);
        if (ok){
            return  "Iva with id" + id_categ + " delete";
        }else{
            return "Error, we have a problem";

        }
    }
     */

    @DeleteMapping(value = "/delete-category/{id}", path = "/delete-category/{id}")
    public String deleteById(@PathVariable("id") Long id_categ) {
        CategoryModel category = categoryService.findById(id_categ);
        if (category != null) {
            category.setEst_categ((byte) 0);  // Establecer el campo est_prom a 0 en lugar de eliminar
            categoryService.updateCategory(category);  // Llamar al método para actualizar la promoción en lugar de eliminarla
            return "Category_product with id " + id_categ + " set to inactive";
        } else {
            return "Category_product  not found";
        }
    }



}
