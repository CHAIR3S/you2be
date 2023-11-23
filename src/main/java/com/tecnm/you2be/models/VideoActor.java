package com.tecnm.you2be.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class VideoActor {

    private int idVideoActor;

    private int idVideo;

    private int idActor;

}
