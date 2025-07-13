package com.alura.literalura.repository;

import com.alura.literalura.model.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    AuthorEntity findByName(String name);
}