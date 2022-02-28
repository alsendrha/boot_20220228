package com.example.service;

import com.example.entity.Sequnce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

// 컨트롤러에서 사용 X
// 다른 service에서 시퀀스가 필요할때 사용할 용도

@Service
public class SequenceService {

    @Autowired
    MongoTemplate mongoDB;

    public long generatorSequnce(String seqName) {

        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(seqName));

        Update update = new Update();
        update.inc("_id", 1);

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        options.upsert(true);

        Sequnce counter = mongoDB.findAndModify(query, update, options, Sequnce.class);

        if (counter != null) {
            return counter.getSeq();
        }
        return 1;
    }

}
