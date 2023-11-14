package com.tecnm.you2be.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Categoria {

    private int idCategoria;

    private String categoria;

    private int idGenero;

}
