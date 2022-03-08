package com.example.repository;

import java.util.List;

import com.example.entity.Book;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, Long> {

    // 기본적인 CRUD(읽기, 쓰기, 수정, 삭제)
    // 1. 책등록
    // 2. 책목록(전체)
    // 3. 책번호를 누르면 책 상세내용 표시

    @Query(value = "{title : {$regex : ?0}}")
    List<Book> getBookList(String title, Pageable Pageable);

    @Query(value = "{title : {$regex : ?0}}", count = true)
    Long getBookCount(String title);

}
