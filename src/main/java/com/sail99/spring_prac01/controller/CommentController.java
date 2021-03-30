package com.sail99.spring_prac01.controller;


import com.sail99.spring_prac01.comment.Comment;
import com.sail99.spring_prac01.comment.CommentRepository;
import com.sail99.spring_prac01.comment.CommentRequestDto;
import com.sail99.spring_prac01.security.UserDetailsImpl;
import com.sail99.spring_prac01.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentRepository commentRepository;
    private final CommentService commentService;

    // 호출 (댓글 리스트)
    @GetMapping("sort/comments/{postId}")
    public List<Comment> findCommentList(@PathVariable Long postId) {
        return commentService.getComment(postId);
    }

    // 댓글 등록
    @Secured("ROLE_USER")
    @PostMapping("/api/comments")
    public Comment createComment(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 현재 로그인 되어있는 사용자의 이름 즉 댓글 작성자

        String writer = userDetails.getUsername();

        Comment comment = commentService.createComment(requestDto, writer);
        return comment;
    }
}
