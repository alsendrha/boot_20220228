package com.example.service;

import java.util.List;

import com.example.entity.Item;

import org.springframework.stereotype.Service;

@Service
public interface ItemDB {

    public Item insertItem(Item item);

    public List<Item> selectListItem();
}
