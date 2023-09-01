package com.example.demo.dto.response;

import java.util.Date;

public record BookResponse(
        String bookName,
        String author,
        Integer totalPage,
        Date createDate
) {
}
