package com.project.repring.controller;

import com.project.repring.dto.PostRequestDto;
import com.project.repring.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/api/posts")
    public ResponseEntity<PostRequestDto> createPost(PostRequestDto requestDto) {
        Long postId = postService.createPost(requestDto);
        return ResponseEntity.created(URI.create("/" + postId)).build();
    }

}
