package com.project.repring.service;

import com.project.repring.dto.PostRequestDto;
import com.project.repring.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    @Transactional
    public Long createPost(PostRequestDto requestDto) {
        return requestDto.toEntity().getId();
    }

}
