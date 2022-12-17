package com.project.repring.service;

import com.project.repring.dto.PostRequestDto;

public interface PostService {

    Long createPost(PostRequestDto requestDto);

}
