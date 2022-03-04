package com.example.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Document(collection = "book4")
public class Book {

    @Id
    private long code = 0L;

    private String title = null;

    private long price = 0L;

    // 문자열 "hello "
    private String writer = null;

    // "A", "B", "C"
    private String category = null;

    private Date regdate = null;

}
