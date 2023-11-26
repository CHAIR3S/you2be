package com.tecnm.you2be.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class Subscripcion {

    private int idSubscripcion;

    private BigDecimal costo;

    private String tipo;

    private int idUsuario;

    private int idPago;

}
