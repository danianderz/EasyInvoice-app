package com.serviciosFacturacion.servicios.services;

import com.serviciosFacturacion.servicios.models.EstablishmentModel;
import com.serviciosFacturacion.servicios.repositories.IEstablishment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class EstablishmentService {
    @Autowired
    IEstablishment iEstablishment;

    public ArrayList<EstablishmentModel> getEstablishmentS() {
        return (ArrayList<EstablishmentModel>) iEstablishment.findAll();
    }

    public EstablishmentModel saveEstablishment(EstablishmentModel establishment){

        return iEstablishment.save(establishment);
    }

    public Optional<EstablishmentModel> getById(Long id_establecimiento  ){
        return  iEstablishment.findById(id_establecimiento );

    }


    @Transactional
    public EstablishmentModel updateById(EstablishmentModel request, Long id_establecimiento) {

        EstablishmentModel establishment = iEstablishment.findById(id_establecimiento).orElse(null);
        if (establishment != null) {
            establishment.setId_tienda_per(request.getId_tienda_per());
            establishment.setNombre(request.getNombre());
            establishment.setTelefono(request.getTelefono());
            establishment.setDireccion(request.getDireccion());
            establishment.setEmail(request.getEmail());
            establishment.setEst_establecimiento(request.getEst_establecimiento());
            System.out.println("-----------------------");
            System.out.println(establishment.getEst_establecimiento());
        }
        return establishment;
    }




    public Boolean deleteEstablis(Long id_establecimiento){
        try {
            iEstablishment.deleteById(id_establecimiento);
            return  true;
        }catch (Exception e){
            return  false;
        }
    }
}
