package com.serviciosFacturacion.servicios.models;

public class LoginRequest {
        private String email_usuario;
        private String contrasenia;

        public String getEmail_usuario() {
            return email_usuario;
        }

        public void setEmail_usuario(String email_usuario) {
            this.email_usuario = email_usuario;
        }

        public String getContrasenia() {
            return contrasenia;
        }

        public void setContrasenia(String contrasenia) {
            this.contrasenia = contrasenia;
        }


}
