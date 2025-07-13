package com.alura.literalura.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private boolean copyright;


    private String language;

    private String mediaType;

    private Integer downloadCount;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<AuthorEntity> authors;


    public BookEntity (){}

    public BookEntity (Book bookData){
        this.title = bookData.title();
        this.language = bookData.languages().getFirst();

//        this.author = new AuthorEntity(bookData.authors().getFirst());
        this.authors = bookData.authors().stream()
                .map(AuthorEntity::new)
                .collect(Collectors.toList());
        this.copyright = bookData.copyright();
        this.mediaType = bookData.mediaType();
        this.downloadCount = bookData.downloadCount();
    }

    // Getters and setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCopyright() {
        return copyright;
    }

    public void setCopyright(boolean copyright) {
        this.copyright = copyright;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public List<AuthorEntity> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorEntity> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", copyright=" + copyright +
                ", language=" + language +
                ", mediaType='" + mediaType + '\'' +
                ", downloadCount=" + downloadCount +
                ", authors=" + authors +
                '}';
    }
}

