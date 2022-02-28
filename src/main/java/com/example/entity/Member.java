package com.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Document(collection = "member3")
public class Member {
    // 화면(insert)에 name의 명칭이랑 같아야함
    @Id
    private String id = null;

    private String pw = null;

    private String pw1 = null;

    private String name = null;

    private int age = 0;
}
