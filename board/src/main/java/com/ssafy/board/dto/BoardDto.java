package com.ssafy.board.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.board.entity.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@ToString
@NoArgsConstructor
public class BoardDto {

    private int id;
    private String title;
    private String writer;
    private String content;
    private int count;
    private LocalDateTime created_date;
    private LocalDateTime updated_date;

    public Board toEntity() {
        Board build = Board.builder()
                .id(id)
                .title(title)
                .writer(writer)
                .content(content)
                .count(count)
                .build();
        return build;

    }

    @Builder
    public BoardDto(int id, String title, String writer, String content, int count) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.count = count;
    }
}
