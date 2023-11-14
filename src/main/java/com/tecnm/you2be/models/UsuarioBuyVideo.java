package com.tecnm.you2be.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UsuarioBuyVideo {

    private int idCompra;

    private double descuento;

    private int idUsuario;

    private int idVideo;

    private int idPago;

}
