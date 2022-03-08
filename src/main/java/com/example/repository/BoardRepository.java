package com.example.repository;

import java.util.Collection;
import java.util.List;

import com.example.entity.Board;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends MongoRepository<Board, Long> {

    // 정확하게 일치 findBy변수명
    // 작성자가 'a'인 사람의 목록 조회
    List<Board> findByTitle(String title);

    List<Board> findByWriter(String writer);

    // 조회수가 100인것 목록으로 조회
    List<Board> findByHit(long hit);

    // 크다 작다
    // 조회수가 200 미만 인것 목록으로 조회
    List<Board> findByHitLessThan(long hit);

    // 조회수가 300 이상 인것 목록으로 조회
    List<Board> findByHitGreaterThanEqual(long hit);

    // 여러개 포함
    // 글번호가 컬렉션에 담긴 1, 5, 7만 조회
    List<Board> findByNoIn(Collection<Long> nos);

    List<Board> findByNoNotIn(Collection<Long> nos);

    // 직접 구현
    // 컨트롤러에서 bRepository.getBoardTitle("제목")
    // mongodb문법
    @Query(value = "{ 'title' : ?0 }")
    List<Board> getBoardTitle(String title);

    @Query(value = "{ 'writer' : ?0 }") // ?0 = String writer
    List<Board> getBoardWriter(String writer);

    @Query(value = "{ 'hit' : ?0 }")
    List<Board> getBoardHit(long hit);

    @Query(value = "{title : {$regex: ?0}}")
    List<Board> getBoardTitleLike(String title);
    // ex) 컨트롤러에서 bRepository.getBoardTitle("가")
    // 제목에 "가" 가 포함된것 가져옴

    // 조회사구 n보다 작다
    @Query(value = "{hit : {$lt : ?0}}")
    List<Board> getBoardHitLt(long hit);

    // 제목과 작성자가 일치(AND)
    @Query(value = "{title : ?0, writer : ?1}")
    // @Query(value = "{$and : [{title : ?0}, {writer : ?1}]}")
    List<Board> getBoardTitleAndWriter(String title, String writer);

    // 제목과 작성자 둘중 하나 (OR)
    @Query(value = "{$or : [{title : ?0}, {writer : ?1}]}")
    List<Board> getBoardTitleOrWriter(String title, String writer);

    // 전체 제목이 포함된것 중에서 숫자
    @Query(value = "{title : {$regex: ?0}}", count = true)
    long getBoardTitleLikeCount(String title);

    // 작성자가 정확하게 일치하는 목록 글번호 내림차순
    @Query(value = "{ 'writer' : ?0 }", sort = "{_id:-1}") // ?0 = String writer
    List<Board> getBoardWriterSort(String writer);

}
