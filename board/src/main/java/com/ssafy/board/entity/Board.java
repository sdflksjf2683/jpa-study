package com.ssafy.board.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends TimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String writer;
    private String content;
    private int count;


    @Builder
    public Board(int id, String title, String writer, String content, int count) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.count = count;

    }

    public void update(int id, String title, String writer, String content, int count) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.count = count;
    }

}
