package com.example.demo.dto.request;

public record BookRequest(
        String bookName,
        String author,
        Integer totalPage
) {
}
