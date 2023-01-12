package com.ssafy.board.controller;

import com.ssafy.board.dto.BoardDto;
import com.ssafy.board.entity.Board;
import com.ssafy.board.repository.BoardRepository;
import com.ssafy.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private BoardService service;

    @GetMapping("/list")
    public List<BoardDto> list(){
        List<BoardDto> blist = service.selectAll();
        return blist;
    }

    @PostMapping
    public ResponseEntity<?> write(@RequestBody BoardDto board) {
        Board newboard = service.insert(board);
        return new ResponseEntity<>(newboard, HttpStatus.ACCEPTED);
    }
}
