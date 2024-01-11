package com.serviciosFacturacion.servicios.repositories;

import com.serviciosFacturacion.servicios.models.EstablishmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEstablishment extends JpaRepository<EstablishmentModel,Long > {
}

