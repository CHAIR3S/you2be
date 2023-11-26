package com.tecnm.you2be.youtube.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Search {
    private String kind;
    private String etag;
    private Id id;
    private Snippet snippet;
}
