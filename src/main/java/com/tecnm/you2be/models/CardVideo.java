package com.tecnm.you2be.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CardVideo {

    private int idVideo;

    private String titulo;

    private String descripcion;

    private String linkImage;

    private String linkVideo;

    private String tipo;

    private double precio;

    private String canal;
}
