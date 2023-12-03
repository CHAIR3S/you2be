package com.tecnm.you2be.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class CardVideo {

    private int idVideo;

    private String titulo;

    private String descripcion;

    private String link;

    private String tipo;

    private double precio;

    private String canal;
}
