package com.alura.literalura.repository;

import com.alura.literalura.model.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    AuthorEntity findByName(String name);

    @Query("SELECT a FROM AuthorEntity a WHERE a.birthYear <= :year AND (a.deathYear IS NULL OR a.deathYear > :year)")
    List<AuthorEntity> findLivingAuthorsInYear(Integer year);
}