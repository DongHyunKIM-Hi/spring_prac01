package com.sail99.spring_prac01.service;


import com.sail99.spring_prac01.domain.Memory;
import com.sail99.spring_prac01.domain.MemoryRepository;
import com.sail99.spring_prac01.domain.MemoryRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@RequiredArgsConstructor
@Service
public class MemoryService {
    private final MemoryRepository  memoryRepository;


    public List<Memory> getMyMemorys(Long userId){
        return memoryRepository.findAllByUserId(userId);
    }

    public List<Memory> getMemorys(){
        return memoryRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional
    public Long update(Long id, MemoryRequestDto requestDto){
        Memory memory = memoryRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("해당하는 아이디가 없소")
        );
        memory.update(requestDto);
        return memory.getId();
    }
    @Transactional
    public void hit(Long id){
        Memory memory = memoryRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("해당하는 아이디가 없네요")
        );
        memory.hit(memory);
    }

    @Transactional
    public Memory createMemory(MemoryRequestDto requestDto, Long userId){
        Memory memory = new Memory(requestDto, userId);
        memoryRepository.save(memory);
        return memory;
    }
}
