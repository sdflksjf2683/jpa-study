package com.ssafy.board.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.board.entity.Board;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@ApiModel(value = "BoardDto : 게시글정보", description = "게시글의 상세 정보를 나타낸다.")
public class BoardDto {

    @ApiParam(value = "글번호", required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ApiParam(value = "글제목", required = true)
    private String title;
    @ApiParam(value = "작성자", required = true)
    private String writer;
    @ApiParam(value = "글내용", required = true)
    private String content;
    @ApiParam(value = "조회수", required = true)
    private int count;

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
