package com.sail99.spring_prac01.service;

import com.sail99.spring_prac01.comment.Comment;
import com.sail99.spring_prac01.comment.CommentRepository;
import com.sail99.spring_prac01.comment.CommentRequestDto;
import com.sail99.spring_prac01.domain.Memory;
import com.sail99.spring_prac01.domain.MemoryRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    // 해당 게시글 댓글 조회
    public List<Comment> getComment(Long postId){
        return commentRepository.findByPostIdOrderByCreatedAt(postId);
    }

    @Transactional
    public Comment createComment(CommentRequestDto requestDto, String writer){
        Comment comment = new Comment(requestDto,writer);

        commentRepository.save(comment);

        return comment;
    }
    @Transactional
    public Long update(Long id, CommentRequestDto requestDto){
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("해당하는 아이디가 없소")
        );
        comment.update(requestDto);
        return comment.getId();
    }

}
