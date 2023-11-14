package com.tecnm.you2be.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Subscripcion {

    private int idSubscripcion;

    private double costo;

    private String tipo;



}
