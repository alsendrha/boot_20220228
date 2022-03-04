package com.example.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
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

    private Date regdate = null;

    // 일시적
    // 컬럼으로 만들어 지지 않음
    // 개발에서 필요한 정보를 보관하기 위한 용도
    @Transient
    private String newPw = null;
}
