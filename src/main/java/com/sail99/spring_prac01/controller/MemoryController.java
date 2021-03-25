package com.sail99.spring_prac01.controller;


import com.sail99.spring_prac01.domain.Memory;
import com.sail99.spring_prac01.domain.MemoryRepository;
import com.sail99.spring_prac01.domain.MemoryRequestDto;
import com.sail99.spring_prac01.service.MemoryService;
import jdk.jfr.Frequency;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class MemoryController {

    private final MemoryRepository memoryRepository;
    private final MemoryService memoryService;

    @PostMapping("/api/memorys")
    public Memory createMemo(@RequestBody MemoryRequestDto requestDto) {
        Memory memo = new Memory(requestDto);
        return memoryRepository.save(memo);
    }

    @GetMapping("/api/memorys/search/{id}")
    public String findMemory(@PathVariable Long id){
        Memory memory = memoryRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("해당하는 아이디가 없습니다.")
        );
        memoryService.hit(id);
        return memory.getContents();
    }

    @GetMapping("/api/memorys")
    public List<Memory> getMemorys(){
        return memoryRepository.findAllByOrderByCreatedAtDesc();
    }

    @GetMapping("/api/memory/view")
    public List<Memory> getMemorysView(){
        return memoryRepository.findAllByOrderByViewDesc();
    }

    @GetMapping("/api/memorys/name/{nickname}")
    public List<Memory> getMemorysNickname(@PathVariable String nickname){
        return memoryRepository.findByNicknameOrderByCreatedAtDesc(nickname);
    }

    @DeleteMapping("/api/memorys/{id}")
    public Long deleteMemory(@PathVariable Long id){
        memoryRepository.deleteById(id);
        return id;
    }
    @PutMapping("/api/memorys/{id}")
    public Long updateMemory(@PathVariable Long id, @RequestBody MemoryRequestDto  requestDto){
        memoryService.update(id,requestDto);
        return id;
    }



}
