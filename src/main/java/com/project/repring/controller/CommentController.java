package com.project.repring.controller;

import com.project.repring.dto.CommentRequestDto;
import com.project.repring.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/{postId}/comments")
    public ResponseEntity<Long> createComment(@PathVariable Long postId, CommentRequestDto commentRequestDto) {

        Long commentId = commentService.createComment(postId, commentRequestDto);
        log.info("commentId = {}", commentId);
        return ResponseEntity.created(URI.create("/" + commentId)).build();

    }
}
