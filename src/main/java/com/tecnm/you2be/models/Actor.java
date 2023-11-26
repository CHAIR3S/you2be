package com.tecnm.you2be.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Actor {

    private int idActor;

    private String nombre;

    private String primerApellido;

    private String segundoApellido;

    private int idRol;

}
