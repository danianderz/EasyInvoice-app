package com.serviciosFacturacion.servicios.services;

import com.serviciosFacturacion.servicios.models.LoginRequest;
import com.serviciosFacturacion.servicios.models.UserModel;
import com.serviciosFacturacion.servicios.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    IUserRepository userRepository;


    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public ArrayList<UserModel> getUsers(){
        return (ArrayList<UserModel>) userRepository.findAll();
    }

    public UserModel saveUser(UserModel user){

        return userRepository.save(user);
    }

    public Optional<UserModel> getById(Long id_usuario  ){
        return  userRepository.findById(id_usuario );

    }


    @Transactional
    public UserModel updateById(UserModel request, Long id_usuario) {

        UserModel user = userRepository.findById(id_usuario).orElse(null);
        if (user != null) {

            user.setId_tip_dni(request.getId_tip_dni());
            user.setId_establ_per(request.getId_establ_per());
            user.setNum_ident(request.getNum_ident());
            user.setNomb_usuario(request.getNomb_usuario());
            user.setApell_usuario(request.getApell_usuario());
            user.setEmail_usuario(request.getEmail_usuario());
            user.setTelef_usuario(request.getTelef_usuario());
            user.setDirec_usuario(request.getDirec_usuario());
            user.setEstado_usuario(request.getEstado_usuario());
            user.setTip_usuario(request.getTip_usuario());
            user.setContrasenia(request.getContrasenia());
        }
        return user;
    }




    public Boolean deleteUser(Long id_usuario){
        try {
            userRepository.deleteById(id_usuario);
            return  true;
        }catch (Exception e){
            return  false;
        }
    }


    public UserModel authenticateUser(String email, String password) {
        String query = "SELECT * FROM usuario WHERE email_usuario = ? and contrasenia = ?";

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{email, password}, (rs, rowNum) -> {
                UserModel user = new UserModel();
                user.setId_usuario(rs.getLong("id_usuario"));
                user.setId_tip_dni(rs.getInt("id_tip_dni"));
                user.setId_establ_per(rs.getInt("id_establ_per"));
                user.setNum_ident(rs.getString("num_ident"));
                user.setNomb_usuario(rs.getString("nomb_usuario"));
                user.setApell_usuario(rs.getString("apell_usuario"));
                user.setEmail_usuario(rs.getString("email_usuario"));
                user.setTelef_usuario(rs.getString("telef_usuario"));
                user.setDirec_usuario(rs.getString("direc_usuario"));
                user.setEstado_usuario(rs.getByte("estado_usuario"));
                user.setTip_usuario(rs.getByte("tip_usuario"));
                user.setContrasenia(rs.getString("contrasenia"));
                return user;
            });
        } catch (EmptyResultDataAccessException e) {
            return null; // Usuario no encontrado
        }
    }


}
