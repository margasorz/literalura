package com.alura.literalura.model;
import jakarta.persistence.*;

@Entity
@Table(name = "authors")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Autogenerado, no viene del JSON

    private String name;
    private Integer birthYear;
    private Integer deathYear;

    public AuthorEntity (){}

    public AuthorEntity (Author authorData){
        this.name = authorData.name();
        this.birthYear = authorData.birthYear();
        this.deathYear = authorData.deathYear();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", nombre='" + name + '\'' +
                ", birthYear=" + birthYear +
                ", deathYear=" + deathYear +
                '}';
    }
}
