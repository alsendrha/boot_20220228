package com.example.service;

import java.util.List;

import com.example.entity.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class ItemDBImpl implements ItemDB {

    @Autowired
    private MongoTemplate mongodb; // db연동

    // 내가 직접 만든 service 모든 시퀀스의 값을 가져올수 있게 구현함.
    @Autowired
    private SequenceService sequenceService;

    @Override
    public Item insertItem(Item item) {
        try {
            item.setCode(sequenceService.generatorSequnce("SEQ_ITEM3_NO"));
            return mongodb.insert(item);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Item> selectListItem() {
        try {
            Query query = new Query();
            return mongodb.find(query, Item.class);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
