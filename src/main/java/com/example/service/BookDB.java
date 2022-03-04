package com.example.service;

import java.util.List;

import com.example.entity.Book;

import org.springframework.stereotype.Service;

@Service
public interface BookDB {
    // 일괄등록
    public int insertBatchBook(List<Book> list);

    // 목록(페이지+검색)
    public List<Book> selectListSearchBook(int page, String text);

    // 페이지네이션용(검색어)
    public long countSearchBook(String text);

    public int deleteBook(long code);
}
