package com.example.demo.service;

import com.example.demo.dto.request.BookRequest;
import com.example.demo.dto.response.BookResponse;

import java.util.List;
import java.util.Map;

public interface BookService {

    //LIST
    List<BookResponse> getAllBook();

    //CREATE
    BookResponse createBook(BookRequest bookRequest);

    //UPDATE
    BookResponse updateBook(String name, BookRequest bookRequest);

    //DELETE
    Map<String, Boolean> deleteBook(String name);

    //SEARCH
    List<BookResponse> searchBookNameAndAuthor(String name);

}
