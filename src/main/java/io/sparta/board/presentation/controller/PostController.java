package io.sparta.board.presentation.controller;

import io.sparta.board.application.dto.request.PostCreationRequestDto;
import io.sparta.board.application.dto.request.PostUpdateRequestDto;
import io.sparta.board.application.dto.response.DeletePostResponseDto;
import io.sparta.board.application.dto.response.PostCreationResponseDto;
import io.sparta.board.application.dto.response.PostUpdateResponseDto;
import io.sparta.board.application.dto.response.ShowPostOneResponseDto;
import io.sparta.board.application.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
@Slf4j
public class PostController {
    private final PostService postService;

    /* 통신 확인용
    @GetMapping
    public String home() {
        log.info("GET /post");
        return "GET /post";
    }
    */

    @PostMapping
    public ResponseEntity<PostCreationResponseDto> create(@Valid @RequestBody PostCreationRequestDto dto) {
        log.info("Post Method - 게시글 작성: dto = {}", dto.toString());
        PostCreationResponseDto responseDto = postService.create(dto);
        return ResponseEntity.ok().body(responseDto);
    }

    // *** @PathVariable 로 전달받은 값을 dto 에 포함할 수 있을까?
    @PatchMapping("/update/{id}")
    public ResponseEntity<PostUpdateResponseDto> update(@RequestBody PostUpdateRequestDto dto, @PathVariable UUID id) {
        log.info("Patch Method - 게시글 수정: dto = {}", dto.toString());
        PostUpdateResponseDto updateDto = postService.update(dto, id);
        return ResponseEntity.ok().body(updateDto);
    }

    // 게시글 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DeletePostResponseDto> delete(@PathVariable UUID id) {
        log.info("Delete Method - 게시글 삭제");
        DeletePostResponseDto delete = postService.delete(id);
        return ResponseEntity.ok().body(delete);
    }

    // 게시글 조회
    @GetMapping("/{id}")
    public ResponseEntity<ShowPostOneResponseDto> findOnePost(@PathVariable UUID id) {
        log.info("Get Method - 게시글 조회");
        ShowPostOneResponseDto onePost = postService.findOnePost(id);
        return ResponseEntity.ok().body(onePost);
    }
}
