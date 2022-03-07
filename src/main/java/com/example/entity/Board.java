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

@Document(collection = "board4")
public class Board {
    @Id
    private long no = 0L;

    private String title;

    private String content;

    private String writer;

    private long hit = 1L;

    private Date regdate;

}
