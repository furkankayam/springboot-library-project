package com.example.demo.controller;

import com.example.demo.dto.request.BookRequest;
import com.example.demo.dto.response.BookResponse;
import com.example.demo.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //LIST
    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBook() {
        return new ResponseEntity<>(bookService.getAllBook(), HttpStatus.OK);
    }

    //CREATE
    @PostMapping
    public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest bookRequest) {
        return new ResponseEntity<>(bookService.createBook(bookRequest), HttpStatus.OK);
    }

    //UPDATE
    @PutMapping("/update/{name}")
    public ResponseEntity<BookResponse> updateBook(String name, BookRequest bookRequest) {
        return new ResponseEntity<>(bookService.updateBook(name, bookRequest), HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Map<String, Boolean>> deleteBook(String name) {
        return new ResponseEntity<>(bookService.deleteBook(name), HttpStatus.OK);
    }

    //SEARCH
    @GetMapping("/search/{name}")
    public ResponseEntity<List<BookResponse>> searchBook(String name) {
        return new ResponseEntity<>(bookService.searchBookNameAndAuthor(name), HttpStatus.OK);
    }

}
