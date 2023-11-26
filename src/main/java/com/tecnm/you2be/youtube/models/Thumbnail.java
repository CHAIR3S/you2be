package com.tecnm.you2be.youtube.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Thumbnail {
    private String url;
    private int width;
    private int height;
}
