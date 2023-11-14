package com.tecnm.you2be.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Pago {

    private int idPago;

    private String metodo;

    private double monto;

    private int idTarjeta;

}
