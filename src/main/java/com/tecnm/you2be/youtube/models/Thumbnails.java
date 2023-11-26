package com.tecnm.you2be.youtube.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Thumbnails {
    @JsonProperty("default")
    private Thumbnail defaultThumbnail;
    private Thumbnail medium;
    private Thumbnail high;
    private Thumbnail standard;
    private Thumbnail maxres;
}
