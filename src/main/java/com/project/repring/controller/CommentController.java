package com.project.repring.controller;

import com.project.repring.dto.CommentRequestDto;
import com.project.repring.dto.CommentResponseDto;
import com.project.repring.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/{postId}/comments")
    public ResponseEntity<Long> createComment(@PathVariable Long postId,
                                              @RequestBody CommentRequestDto commentRequestDto) {

        Long commentId = commentService.createComment(postId, commentRequestDto);
        log.info("commentId = {}", commentId);
        return ResponseEntity.created(URI.create("/" + commentId)).build();

    }

    @GetMapping("/api/{postId}/comments")
    public ResponseEntity<List<CommentResponseDto>> findAllComments(@PathVariable Long postId) {
        log.info("postId = {}, commentsCount = {}", postId, commentService.findAllComments(postId).size());
        return ResponseEntity.ok().body(commentService.findAllComments(postId));
    }

    @PutMapping("/api/{postId}/comments/{commentId}")
    public ResponseEntity<Void> updateComment(@PathVariable Long commentId,
                                              @RequestBody CommentRequestDto commentRequestDto) {

        log.info("commentId = {}", commentId);
        commentService.updateComment(commentId, commentRequestDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/{postId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        log.info("commentId = {}", commentId);
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }

}
