package com.tecnm.you2be.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Tarjeta {

    private int idTarjeta;

    private String cvv;

    private String numero;

    private String tipo;

}
