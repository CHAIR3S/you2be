package com.tecnm.you2be.youtube.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Id {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String kind;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String videoId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String channelId;
}
