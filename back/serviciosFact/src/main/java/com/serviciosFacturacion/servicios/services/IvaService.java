package com.serviciosFacturacion.servicios.services;

import com.serviciosFacturacion.servicios.models.IvaModel;
import com.serviciosFacturacion.servicios.repositories.IIVaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class IvaService {
    @Autowired
    IIVaRepository iVaRepository;

    public ArrayList<IvaModel> getIvas(){
        return (ArrayList<IvaModel>) iVaRepository.findAll();
    }

    public IvaModel saveIva(IvaModel iva){

        return iVaRepository.save(iva);
    }

    public Optional<IvaModel> getById(Long id_categ_iva  ){
        return  iVaRepository.findById(id_categ_iva );

    }


    @Transactional
    public IvaModel updateById(IvaModel request, Long id_categ_iva) {

        IvaModel iva = iVaRepository.findById(id_categ_iva).orElse(null);
        if (iva != null) {

            iva.setNomb_categ_iva(request.getNomb_categ_iva());
            iva.setValor(request.getValor());
            iva.setEst_categ_iva(request.getEst_categ_iva());
        }
        return iva;
    }




    public Boolean deleteIva(Long id_categ_iva){
        try {
            iVaRepository.deleteById(id_categ_iva);
            return  true;
        }catch (Exception e){
            return  false;
        }
    }

    public IvaModel updateIva(IvaModel iva) {
        return iVaRepository.save(iva);
    }

    public IvaModel findById(Long id_categ_iva ) {
        return iVaRepository.findById(id_categ_iva).orElse(null);
    }


}
