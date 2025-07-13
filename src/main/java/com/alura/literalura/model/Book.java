package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Book(
        @JsonAlias("title") String title,
        @JsonAlias("authors") List<Author> authors,
        @JsonAlias("subjects") List<String> subjects,
        @JsonAlias("bookshelves") List<String> bookshelves,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("copyright") boolean copyright,
        @JsonAlias("media_type") String mediaType,
        @JsonAlias("formats") Map<String, String> formats,
        @JsonAlias("download_count") int downloadCount
) {

}
