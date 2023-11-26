package com.tecnm.you2be.youtube.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
public class Snippet {

    private String publishedAt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String channelId;

    private String title;
    private String description;
    private Thumbnails thumbnails;
    private String channelTitle;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> tags;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String categoryId;

    private String liveBroadcastContent;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String publishTime;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Localized localized;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String defaultAudioLanguage;

}
