package com.ssafy.board.service;

import com.ssafy.board.dto.BoardDto;
import com.ssafy.board.entity.Board;
import com.ssafy.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private BoardRepository boardRepository;

    public Board insert(BoardDto dto) {
        return boardRepository.save(dto.toEntity());
    }

    public List<BoardDto> selectAll() {
        List<Board> boards = boardRepository.findAll();
        List<BoardDto> blist = new ArrayList<>();

        for(Board b : boards) {
            BoardDto dto = BoardDto.builder()
                    .id(b.getId())
                    .title(b.getTitle())
                    .writer(b.getWriter())
                    .content(b.getContent())
                    .count(b.getCount())
                    .build();
            blist.add(dto);
        }

        return blist;
    }

}
