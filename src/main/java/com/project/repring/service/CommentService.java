package com.project.repring.service;

import com.project.repring.domain.Comment;
import com.project.repring.domain.Post;
import com.project.repring.dto.CommentRequestDto;
import com.project.repring.exception.CustomException;
import com.project.repring.exception.ErrorCode;
import com.project.repring.repository.CommentRepository;
import com.project.repring.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Long createComment(Long post_id, CommentRequestDto commentRequestDto) {

        Post post = postRepository.findById(post_id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_POST));

        Comment comment = Comment.builder()
                .post(post)
                .comment(commentRequestDto.getComment())
                .build();
        commentRepository.save(comment);

        return comment.getId();

    }
}
