package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    Optional<Book> getByBookName(String name);

    @Query("SELECT b FROM Book b WHERE b.bookName LIKE %:name% OR b.author LIKE %:name%")
    List<Book> searchBookNameOrAuthor(@RequestParam("name") String name);

}
