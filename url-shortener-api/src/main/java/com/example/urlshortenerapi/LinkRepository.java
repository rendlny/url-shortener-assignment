package com.example.urlshortenerapi;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface LinkRepository extends JpaRepository<Link, Long> {

    @Query(value = "SELECT * FROM link l WHERE l.code = ?1", nativeQuery = true)
    Optional<Link> findByCode(String code);

}