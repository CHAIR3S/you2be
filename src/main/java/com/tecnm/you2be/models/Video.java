package com.tecnm.you2be.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class Video {

    private int idVideo;

    private String titulo;

    private String descripcion;

    private String link;

    private String tipo;

    private BigDecimal precio;

    private int idCanal;

}
