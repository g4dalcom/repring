package com.project.repring.controller;

import com.project.repring.dto.PostRequestDto;
import com.project.repring.dto.PostResponseDto;
import com.project.repring.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/api/posts")
    public ResponseEntity<Void> createPost(@RequestBody PostRequestDto requestDto) {
        Long postId = postService.createPost(requestDto);
        log.info("postId = {}", postId);

        return ResponseEntity.created(URI.create("/" + postId)).build();
    }

    @GetMapping("/api/posts")
    public ResponseEntity<List<PostResponseDto>> findAllPosts() {
        log.info("post count = {}", postService.findAllPosts().size());
        return ResponseEntity.ok().body(postService.findAllPosts());
    }

    @GetMapping("/api/posts/{postId}")
    public ResponseEntity<PostResponseDto> findPost(@PathVariable Long postId) {
        log.info("post ID = {}", postId);
        return ResponseEntity.ok().body(postService.findPost(postId));
    }

    @PutMapping("/api/posts/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long postId,
                                                      @RequestBody PostRequestDto postRequestDto) {
        log.info("post ID = {}", postId);
        return ResponseEntity.ok().body(postService.updatePost(postId, postRequestDto));
    }

    @DeleteMapping("/api/posts/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        log.info("post ID = {}", postId);
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

}

