package com.project.repring.service;

import com.project.repring.domain.Member;
import com.project.repring.domain.Post;
import com.project.repring.dto.PostDto;
import com.project.repring.exception.CustomException;
import com.project.repring.jwt.UserDetailsImpl;
import com.project.repring.repository.MemberRepository;
import com.project.repring.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.project.repring.exception.ErrorCode.NOT_FOUND_POST;
import static com.project.repring.exception.ErrorCode.NOT_FOUND_USER;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long createPost(PostDto.Request requestDto, UserDetailsImpl userDetails) {

        Member member = memberRepository.findByUsername(userDetails.getUsername()).orElse(null);
        if (member == null) {
            throw new CustomException(NOT_FOUND_USER);
        }

        Post post = requestDto.toEntity();
        postRepository.save(post);

        return post.getId();
    }

    @Transactional
    public List<PostDto.Response> findAllPosts() {
        List<Post> posts = postRepository.findAll();

        List<PostDto.Response> responseDtoList = posts.stream().map(p -> PostDto.Response.builder()
                .id(p.getId())
                .title(p.getTitle())
                .content(p.getContent())
                .build()).collect(Collectors.toList());

        return responseDtoList;

    }

    @Transactional
    public PostDto.Response findPost(Long post_id) {
        Post post = postRepository.findById(post_id).orElseThrow(() -> new CustomException(NOT_FOUND_POST));

        return PostDto.Response.of(post);

    }

    @Transactional
    public PostDto.Response updatePost(Long post_id, PostDto.Request postRequestDto) {
        Post post = postRepository.findById(post_id).orElseThrow(() -> new CustomException(NOT_FOUND_POST));

        post.update(postRequestDto);
        postRepository.save(post);

        return PostDto.Response.of(post);
    }

    @Transactional
    public void deletePost(Long post_id) {
        Post post = postRepository.findById(post_id).orElseThrow(() -> new CustomException(NOT_FOUND_POST));
        postRepository.delete(post);
    }
}
