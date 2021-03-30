package com.sail99.spring_prac01.controller;


import com.sail99.spring_prac01.domain.Memory;
import com.sail99.spring_prac01.domain.MemoryRepository;
import com.sail99.spring_prac01.domain.MemoryRequestDto;
import com.sail99.spring_prac01.security.UserDetailsImpl;
import com.sail99.spring_prac01.service.MemoryService;
import jdk.jfr.Frequency;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class MemoryController {

    private final MemoryRepository memoryRepository;
    private final MemoryService memoryService;

    //생성
    @Secured("ROLE_USER")
    @PostMapping("/api/memorys")
    public Memory createMemo(@RequestBody MemoryRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        Memory memory = memoryService.createMemory(requestDto, userId);
        return memory;
    }

    // 게시글 삭제
    @Secured("ROLE_USER")
    @DeleteMapping("/api/memorys/{id}")
    public Long deleteMemory(@PathVariable Long id) {
        memoryRepository.deleteById(id);
        return id;
    }

    // 게시글 수정하기
    @Secured("ROLE_USER")
    @PutMapping("/api/memorys/{id}")
    public Long updateMemory(@PathVariable Long id, @RequestBody MemoryRequestDto requestDto) {
        memoryService.update(id, requestDto);
        return id;
    }


    // 내 글 조회
    @Secured("ROLE_USER")
    @GetMapping("/api/my_meorys")
    public List<Memory> getMyMemorys(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        return memoryService.getMyMemorys(userId);
    }




//////////////////////////////////////////////////////////////////////////





    //게시글 보기
    @GetMapping("/sort/memorys/search/{id}")
    public String findMemory(@PathVariable Long id) {
        Memory memory = memoryRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당하는 아이디가 없습니다.")
        );
        memoryService.hit(id);
        return memory.getContents();
    }


    // 등록순으로 정렬
    @GetMapping("/sort/make")
    public List<Memory> getMemorys() {
        return memoryService.getMemorys();
    }

    // 조회순으로 정렬
    @GetMapping("/sort/view")
    public List<Memory> getMemorysView() {
        return memoryRepository.findAllByOrderByViewDesc();
    }

    //아이디 조회
    @GetMapping("/sort/memorys/name/{nickname}")
    public List<Memory> getMemorysNickname(@PathVariable String nickname) {
        return memoryRepository.findByNicknameOrderByCreatedAtDesc(nickname);
    }


}
