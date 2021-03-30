package com.sail99.spring_prac01.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByNicknameOrderByCreatedAt(String nickname);
}
