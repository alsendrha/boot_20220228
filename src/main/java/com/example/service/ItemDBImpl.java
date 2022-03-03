package com.example.service;

import java.util.Date;
import java.util.List;

import com.example.entity.Item;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ItemDBImpl implements ItemDB {

    // DB연결객체
    @Autowired
    private MongoTemplate mongoDB;

    // 직접만든 시퀀스용 서비스
    @Autowired // Autowired로 만들면 무조건 Autowired 써야됨
    private SequenceService sequence;

    @Override
    public int insertItem(Item item) {
        try {
            // 시퀀스 가져오기(없으면 생성됨)
            long seq = sequence.generatorSequence("SEQ_ITEM4_CODE");
            item.setCode(seq);

            // import java.util.Date;
            Date date = new Date(); // 현재시간 만들기
            item.setRegdate(date);

            Item item1 = mongoDB.insert(item);
            if (item1.getCode() == seq) {
                return 1;
            }
            return 0;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    @Override
    public List<Item> selectListItem(Pageable pageable) {
        try {
            Query query = new Query();
            query.with(pageable);// skip().limit()
            query.fields().exclude("filedata", "filetype", "filesize", "filename");
            Sort sort = Sort.by(Direction.DESC, "_id");
            query.with(sort);

            return mongoDB.find(query, Item.class);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public int deleteItem(long code) {
        try {
            Query query = new Query();
            Criteria criteria = Criteria.where("_id").is(code);
            query.addCriteria(criteria);
            DeleteResult result = mongoDB.remove(query, Item.class);
            if (result.getDeletedCount() == 1L) {
                return 1;
            }
            return 0;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public Item selectOneItemImage(long code) {
        try {
            // 조건 물품번호가 일치하는 것 1개 가져오기
            Query query = new Query();
            Criteria criteria = Criteria.where("_id").is(code);
            query.addCriteria(criteria);

            // projection
            query.fields().include("filedata", "filetype", "filesize");

            return mongoDB.findOne(query, Item.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Item selectOneItem(long code) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(code));

            return mongoDB.findOne(query, Item.class);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public int updateItem(Item item) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(item.getCode()));

            Update update = new Update();
            update.set("name", item.getName());
            update.set("price", item.getPrice());
            update.set("quantity", item.getQuantity());
            if (item.getFilesize() > 0) { // 파일 첨부되었다면
                update.set("filedata", item.getFiledata());
                update.set("filesize", item.getFilesize());
                update.set("filetype", item.getFiletype());
                update.set("filename", item.getFilename());
            }

            UpdateResult result = mongoDB.updateFirst(query, update, Item.class);
            if (result.getModifiedCount() == 1L) {
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public long selectItemCount() {
        try {
            Query query = new Query();
            return mongoDB.count(query, Item.class);

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
