package com.project.repring.domain;

import com.project.repring.dto.CommentRequestDto;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    protected Comment() {
    }

    @Builder
    public Comment(final String comment, final Post post) {
        this.comment = comment;
        this.post = post;
    }

    public void update(final CommentRequestDto commentRequestDto) {
        this.comment = commentRequestDto.getComment();
    }
}
