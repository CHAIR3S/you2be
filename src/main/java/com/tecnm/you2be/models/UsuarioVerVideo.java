package com.tecnm.you2be.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UsuarioVerVideo {

    private int idUsuarioVideo;

    private int idUsuario;

    private int idVideo;

    private int idStatus;

}
