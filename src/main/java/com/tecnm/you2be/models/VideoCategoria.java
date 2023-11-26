package com.tecnm.you2be.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class VideoCategoria {

    private int idVideoCategoria;

    private int idVideo;

    private int idCategoria;

}
