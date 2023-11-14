package com.tecnm.you2be.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Video {

    private int idVideo;

    private String titulo;

    private String descripcion;

    private String link;

    private String tipo;

    private double precio;

    private int idCanal;

}
