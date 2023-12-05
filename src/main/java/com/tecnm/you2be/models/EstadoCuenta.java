package com.tecnm.you2be.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoCuenta {

    private int idVideo;

    private String titulo;

    private String descripcion;

    private String tipo;

    private BigDecimal precio;

    private BigDecimal precioTotal;

}
