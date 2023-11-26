package com.tecnm.you2be.youtube.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Data
@RequiredArgsConstructor
public class Video {

    private String kind;
    private String etag;
    private String id;
    private Snippet snippet;
    private Statistics statistics;
    private Player player;

}
