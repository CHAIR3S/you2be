package com.tecnm.you2be.youtube.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Statistics {

    private String viewCount;
    private String likeCount;
    private String favoriteCount;
    private String commentCount;

}
