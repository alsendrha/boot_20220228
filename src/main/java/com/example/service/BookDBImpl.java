package com.example.service;

import java.util.Collection;
import java.util.List;

import com.example.entity.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

// 1. 서비스
@Service
public class BookDBImpl implements BookDB { // 2. 설계 인터페이스 구현

    // 3. DB연결 객체 생성
    @Autowired
    // private 생략가능
    MongoTemplate mongDB;

    @Override
    public int insertBatchBook(List<Book> list) {
        try {
            // 4. 실제 수행
            Collection<Book> retList = mongDB.insert(list, Book.class);
            if (retList.size() == list.size()) {
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    @Override
    public List<Book> selectListSearchBook(int page, String text) {
        try {
            Query query = new Query();

            // 검색패턴( .*a.* => a가 포함된 것 해당), regex정규식(아무거나(앞) + text + 아무거나(뒤))
            Criteria criteria = Criteria.where("title").regex(".*" + text + ".*");
            query.addCriteria(criteria);

            // 페이지네이션(0 부터 시작)
            Pageable pageable = PageRequest.of(page - 1, 10);
            query.with(pageable);

            // 정렬(_id기준 내림차순)
            Sort sort = Sort.by(Direction.DESC, "_id");
            query.with(sort);

            return mongDB.find(query, Book.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    @Override
    public long countSearchBook(String text) {
        try {
            Query query = new Query();

            // 검색패턴( .*a.* => a가 포함된 것 해당), regex정규식(아무거나(앞) + text + 아무거나(뒤))
            Criteria criteria = Criteria.where("title").regex(".*" + text + ".*");
            query.addCriteria(criteria);

            return mongDB.count(query, Book.class);

        } catch (Exception e) {
            e.printStackTrace();
            return -1;

        }
    }

}
