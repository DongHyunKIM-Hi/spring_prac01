package com.sail99.spring_prac01.comment;


import com.sail99.spring_prac01.domain.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@NoArgsConstructor
@Getter
@Entity
public class Comment extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String single_comment;
    @Column(nullable = false)
    private String writer;
    @Column(nullable = false)
    private Long postId;


    public Comment(String nickname, String single_comment, String writer, Long postId){
        this.nickname = nickname;
        this.single_comment = single_comment;
        this.writer = writer;
        this.postId = postId;
    }
    public Comment(CommentRequestDto requestDto, String writer){
        this.nickname = requestDto.getNickname();
        this.single_comment = requestDto.getSingle_comment();
        this.writer = writer;
        this.postId= requestDto.getPostId();
    }
}

