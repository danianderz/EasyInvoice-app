package com.serviciosFacturacion.servicios.controllers;

import com.serviciosFacturacion.servicios.models.LoginRequest;
import com.serviciosFacturacion.servicios.models.UserModel;
import com.serviciosFacturacion.servicios.repositories.IUserRepository;
import com.serviciosFacturacion.servicios.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController

@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/get-user")
    public ArrayList<UserModel> getUsers(){
        return this.userService.getUsers();
    }

    @PostMapping("/save-user")
    public  UserModel saveUser(@RequestBody UserModel  user){
        //System.out.println(user.getId_tip_dni() + " tipo4");
        //System.out.println(user.getId_establ_per() + " estab");
        //System.out.println(user.getApell_usuario() + " apelli");
        return this.userService.saveUser(user);
    }


    @GetMapping(path = "/{id}")
    public Optional<UserModel> getUserById(@PathVariable("id") Long id_usuario ){
        return  this.userService.getById(id_usuario);
    }

    @PutMapping(value = "/modif-user/{id}", path = "/modif-user/{id}")
    public UserModel updateUserById(@RequestBody UserModel request,@PathVariable("id") Long id_usuario){
        return this.userService.updateById(request, id_usuario);
    }


    @DeleteMapping(value = "/delete-user/{id}", path = "/delete-user/{id}")
    public String deleteById(@PathVariable("id") Long id_usuario){
        boolean ok = this.userService.deleteUser(id_usuario);
        if (ok){
            return  "Iva with id " + id_usuario + " delete";
        }else{
            return "Error, we have a problem";

        }
    }


    @PostMapping("/login")
    public ResponseEntity<UserModel> login(@RequestBody LoginRequest loginRequest) {
        // Verificar las credenciales del usuario
        UserModel user = userService.authenticateUser(loginRequest.getEmail_usuario(), loginRequest.getContrasenia());

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            System.out.println("Acceso denegado");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
