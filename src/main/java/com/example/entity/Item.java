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
@Document(collection = "item4")
public class Item {
    @Id
    private long code = 0L;

    @Field(name = "name") // 만들고싶은 이름 똑같이 할땐 안써도됨
    private String name = null;

    private long price = 0L;

    private long quantity = 0L;

    // import java.util
    private Date regdate = null;

    private byte[] filedata = null;
    private String filetype = null;
    private String filename = null;
    private long filesize = 0L;
}
