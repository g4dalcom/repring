package com.project.repring.service;

import com.project.repring.domain.Post;
import com.project.repring.dto.PostRequestDto;
import com.project.repring.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
