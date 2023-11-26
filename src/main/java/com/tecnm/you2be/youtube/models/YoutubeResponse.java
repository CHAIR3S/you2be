package com.tecnm.you2be.youtube.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class YoutubeResponse<T> {

    private String kind;

    private String etag;

    private List<T> items;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String nextPageToken;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String prevPageToken;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String regionCode;

    private PageInfo pageInfo;

}
