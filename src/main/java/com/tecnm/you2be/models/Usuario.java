package com.tecnm.you2be.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@Data
@RequiredArgsConstructor
public class Usuario {

    private int idUsuario;

    private String nombre;

    private String primerApellido;

    private String segundoApellido;

    private String email;

    private String password;

    private Date nacimiento;

}
