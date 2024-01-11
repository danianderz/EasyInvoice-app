package com.serviciosFacturacion.servicios.repositories;

import com.serviciosFacturacion.servicios.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserModel,Long > {
}