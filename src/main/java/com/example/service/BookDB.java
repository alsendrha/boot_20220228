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

    // 일괄삭제
    public long deleteBatchBook(List<Long> code);

    // 코드에 해당하는 목록 가져오기
    public List<Book> selectListWhereIn(List<Long> code);

    // 일괄수정
    public long updateBactchBook(List<Book> list);

    // 삭제
    public int deleteBook(long code);
}
