package com.example.demo.service.imp;

import com.example.demo.dto.mapper.BookMapper;
import com.example.demo.dto.request.BookRequest;
import com.example.demo.dto.response.BookResponse;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookResponse> getAllBook() {
        List<Book> bookList = bookRepository.findAll();
        List<BookResponse> bookResponseList = bookList
                .stream()
                .map(BookMapper.INSTANCE::toBookResponse)
                .toList();
        return bookResponseList;
    }

    @Override
    public BookResponse createBook(BookRequest bookRequest) {
        Book book = BookMapper.INSTANCE.toBook(bookRequest);
        bookRepository.save(book);
        return BookMapper.INSTANCE.toBookResponse(book);
    }

    @Override
    public BookResponse updateBook(String name, BookRequest bookRequest) {
        Book book = isThereBook(name);
        book.setBookName(bookRequest.bookName());
        book.setAuthor(bookRequest.author());
        book.setTotalPage(bookRequest.totalPage());
        bookRepository.save(book);
        return BookMapper.INSTANCE.toBookResponse(book);
    }

    @Override
    public Map<String, Boolean> deleteBook(String name) {
        Map<String, Boolean> response = new HashMap<>();
        bookRepository.delete(isThereBook(name));
        response.put("Delete: ", Boolean.TRUE);
        return response;
    }

    @Override
    public List<BookResponse> searchBookNameAndAuthor(String name) {
        List<Book> bookList = bookRepository.searchBookNameOrAuthor(name);
        return bookList.stream()
                .map(BookMapper.INSTANCE::toBookResponse)
                .collect(Collectors.toList());
    }

    private Book isThereBook(String name) {
        return bookRepository.getByBookName(name)
                .orElseThrow(
                        () -> new RuntimeException("Book Not Found!")
                );
    }

}
