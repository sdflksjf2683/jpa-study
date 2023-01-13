package com.ssafy.board.controller;

import com.ssafy.board.dto.BoardDto;
import com.ssafy.board.entity.Board;
import com.ssafy.board.repository.BoardRepository;
import com.ssafy.board.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@Api("게시판 컨트롤러 API V1")
public class BoardController {

    @Autowired
    private BoardService service;

    @ApiOperation(value = "게시판 글 목록 조회", notes = "모든 게시글 목록을 조회한다.", response = String.class)
    @GetMapping("/list")
    public ResponseEntity<List<BoardDto>> selectAll(){
        List<BoardDto> blist = service.selectAll();
        if(blist!=null) {
            return new ResponseEntity<List<BoardDto>>(blist, HttpStatus.OK);
        }
        return new ResponseEntity<List<BoardDto>>(blist, HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "특정 게시글 조회", notes = "게시글 번호로 특정 게시글 정보를 조회한다. ", response = String.class)
    @GetMapping("/list/{id}")
    public ResponseEntity<BoardDto> selectOne(@ApiParam(value = "int(id)", required = true) @PathVariable int id) {
        BoardDto board = service.selectOne(id);
        if(board != null) {
            return new ResponseEntity<>(board, HttpStatus.OK);
        }
        return new ResponseEntity<>(board, HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "게시판 글 작성", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    @PostMapping
    public ResponseEntity<String> write(@ApiParam(value = "BoardDto", required = true) @RequestBody BoardDto dto) {
        if(service.insert(dto)>0) {
            return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        }
        return new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "게시판 글 수정", notes = "게시글 내용을 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@ApiParam(value = "int(id)", required = true) @PathVariable int id, @ApiParam(value = "BoardDto", required = true) @RequestBody BoardDto dto) {
        if(service.update(id, dto)>0) {
            return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        }
        return new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);
    }
    @ApiOperation(value = "게시판 글 삭제", notes = "특정 게시글을 삭제한다.", response = String.class)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@ApiParam(value = "int(id)", required = true) @PathVariable int id) {
        if(service.delete(id))
            return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        return new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);

    }
}
