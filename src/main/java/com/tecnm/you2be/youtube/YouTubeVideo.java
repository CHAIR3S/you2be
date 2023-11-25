package com.tecnm.you2be.youtube;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Data
@RequiredArgsConstructor
public class YouTubeVideo {

    private List<String> thumbnails;
    private List<YouTubeMedia> medias;
    private String webPlayerUrl;
    private String embeddedWebPlayerUrl;


    public String retrieveHttpLocation() {
        if (medias==null || medias.isEmpty()) {
            return null;
        }
        for (YouTubeMedia media : medias) {
            String location = media.getLocation();
            if (location.startsWith("http")) {
                return location;
            }
        }
        return null;
    }


}
