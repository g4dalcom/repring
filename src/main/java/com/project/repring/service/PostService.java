package com.project.repring.service;

import com.project.repring.domain.Post;
import com.project.repring.dto.PostRequestDto;
import com.project.repring.dto.PostResponseDto;
import com.project.repring.exception.CustomException;
import com.project.repring.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.project.repring.exception.ErrorCode.NOT_FOUND_POST;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long createPost(PostRequestDto requestDto) {

        Post post = requestDto.toEntity();
        postRepository.save(post);

        return post.getId();
    }

    @Transactional
    public List<PostResponseDto> findAllPosts() {
        List<Post> posts = postRepository.findAll();

        List<PostResponseDto> responseDtoList = posts.stream().map(p -> PostResponseDto.builder()
                .id(p.getId())
                .title(p.getTitle())
                .content(p.getContent())
                .build()).collect(Collectors.toList());

        return responseDtoList;

    }

    @Transactional
    public PostResponseDto findPost(Long post_id) {
        Post post = postRepository.findById(post_id).orElseThrow(() -> new CustomException(NOT_FOUND_POST));

        return PostResponseDto.of(post);

    }

    @Transactional
    public PostResponseDto updatePost(Long post_id, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(post_id).orElseThrow(() -> new CustomException(NOT_FOUND_POST));

        post.update(postRequestDto);
        postRepository.save(post);

        return PostResponseDto.of(post);
    }

    @Transactional
    public void deletePost(Long post_id) {
        Post post = postRepository.findById(post_id).orElseThrow(() -> new CustomException(NOT_FOUND_POST));
        postRepository.delete(post);
    }
}
