package com.sail99.spring_prac01.service;

import com.sail99.spring_prac01.comment.Comment;
import com.sail99.spring_prac01.comment.CommentRepository;
import com.sail99.spring_prac01.comment.CommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    // 해당 게시글 댓글 조회
    public List<Comment> getComment(String nickname){
        return commentRepository.findByNicknameOrderByCreatedAt(nickname);
    }

    @Transactional
    public Comment createComment(CommentRequestDto requestDto, String writer){
        Comment comment = new Comment(requestDto,writer);

        commentRepository.save(comment);

        return comment;
    }

}
