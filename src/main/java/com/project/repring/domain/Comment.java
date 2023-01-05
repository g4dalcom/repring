package com.project.repring.domain;

import com.project.repring.dto.CommentDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Comment(final String comment, final Post post) {
        this.comment = comment;
        this.post = post;
    }

    public void update(final CommentDto.Request commentRequestDto) {
        this.comment = commentRequestDto.getComment();
    }
}
