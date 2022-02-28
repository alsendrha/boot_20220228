package com.example.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Document(collection = "item3")
public class Item {
    @Id
    private long code = 0L;

    @Field(name = "itemname")
    private String name = null;

    @Field(name = "itemprice")
    private long price = 0L;

    @Field(name = "quantity")
    private long quantity = 0L;

    @Field(name = "regdate")
    private Date regdate = null;
}
