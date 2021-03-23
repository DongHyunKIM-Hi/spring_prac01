package com.sail99.spring_prac01.service;


import com.sail99.spring_prac01.domain.Memory;
import com.sail99.spring_prac01.domain.MemoryRepository;
import com.sail99.spring_prac01.domain.MemoryRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@RequiredArgsConstructor
@Service
public class MemoryService {
    private final MemoryRepository  memoryRepository;


    @Transactional
    public Long update(Long id, MemoryRequestDto requestDto){
        Memory memory = memoryRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("해당하는 아이디가 없소")
        );
        memory.update(requestDto);
        return memory.getId();
    }
}
