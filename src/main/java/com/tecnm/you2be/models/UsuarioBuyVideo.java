package com.tecnm.you2be.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class UsuarioBuyVideo {

    private int idCompra;

    private BigDecimal descuento;

    private int idUsuario;

    private int idVideo;

    private int idPago;

}
