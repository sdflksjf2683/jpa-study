package com.ssafy.board.service;

import com.ssafy.board.dto.BoardDto;
import com.ssafy.board.entity.Board;
import com.ssafy.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;


    public int insert(BoardDto dto) {
        Board b = boardRepository.save(dto.toEntity());
        return b.getId();
    }

    public BoardDto selectOne(int id) {
        Optional<Board> board = boardRepository.findById(id);
        Board b = board.get();

        BoardDto dto = BoardDto.builder()
                .title(b.getTitle())
                .writer(b.getWriter())
                .content(b.getContent())
                .count(b.getCount())
                .build();
        return dto;
    }

    public List<BoardDto> selectAll() {
        List<Board> boards = boardRepository.findAll();
        List<BoardDto> blist = new ArrayList<>();

        for(Board b : boards) {
            BoardDto dto = BoardDto.builder()
                    .title(b.getTitle())
                    .writer(b.getWriter())
                    .content(b.getContent())
                    .count(b.getCount())
                    .build();
            blist.add(dto);
        }

        return blist;
    }

    @Transactional
    public int update(int id, BoardDto dto) {
        Optional<Board> board = boardRepository.findById(id);
        int id1 = boardRepository.findById(id).orElseThrow().getId();
        board.get().update(id, dto.getTitle(), dto.getWriter(), dto.getContent(), dto.getCount());

        return id;
    }

    @Transactional
    public boolean delete(int id) {
        Optional<Board> board = boardRepository.findById(id);
        if(board == null) return false;

        boardRepository.deleteById(id);
        return true;
    }

}