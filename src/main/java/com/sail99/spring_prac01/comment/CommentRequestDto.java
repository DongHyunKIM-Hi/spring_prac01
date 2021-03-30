package com.sail99.spring_prac01.comment;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String nickname;
    private String single_comment;
    private String writer;
}
