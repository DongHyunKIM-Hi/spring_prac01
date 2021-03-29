package com.sail99.spring_prac01.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoryRepository extends JpaRepository<Memory,  Long> {
List<Memory>  findAllByOrderByCreatedAtDesc();
List<Memory>  findAllByOrderByViewDesc();
List<Memory>  findByNicknameOrderByCreatedAtDesc(String nickname);
List<Memory>  findAllByUserId(Long userId);
}
