package com.example.service;

import java.util.Collection;
import java.util.List;

import com.example.entity.Book;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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

    @Override
    public long deleteBatchBook(List<Long> code) {
        try {

            // long[] => List<long>
            // code => [2, 5, 3] => collection<Long>
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").in(code));

            DeleteResult result = mongDB.remove(query, Book.class);
            if (result.getDeletedCount() == (long) code.size()) {
                return 1;
            }
            return 0;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;

        }
    }

    @Override
    public List<Book> selectListWhereIn(List<Long> code) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").in(code));

            // DESC 내림차순, ASC 오름차순
            Sort sort = Sort.by(Direction.DESC, "_id"); // 내림차순정렬
            query.with(sort);

            return mongDB.find(query, Book.class);

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    @Override
    public long updateBactchBook(List<Book> list) {
        try {
            long updateCount = 0;
            for (Book tmp : list) {
                Query query = new Query();
                query.addCriteria(Criteria.where("_id").is(tmp.getCode()));

                Update update = new Update();
                update.set("title", tmp.getTitle());
                update.set("price", tmp.getPrice());
                update.set("writer", tmp.getWriter());
                update.set("category", tmp.getCategory());

                UpdateResult result = mongDB.updateFirst(query, update, Book.class);
                updateCount += result.getMatchedCount();
            }
            if (updateCount == list.size()) {
                return 1;
            }
            return 0;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;

        }
    }

    @Override
    public int deleteBook(long code) {
        try {
            Query query = new Query();
            Criteria criteria = Criteria.where("_id").is(code);
            query.addCriteria(criteria);

            DeleteResult result = mongDB.remove(query, Book.class);
            if (result.getDeletedCount() == 1L) {
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;

        }
    }

}
