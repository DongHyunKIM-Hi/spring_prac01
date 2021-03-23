package com.sail99.spring_prac01.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Memory extends Timestamped{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private  String title;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String contents;

    public Memory(String title,String nickname, String contents) {
        this.title = title;
        this.nickname = nickname;
        this.contents = contents;
    }

    public Memory(MemoryRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.nickname = requestDto.getNickname();
        this.contents = requestDto.getContents();
    }
    public void update(MemoryRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.nickname = requestDto.getNickname();
        this.contents = requestDto.getContents();
    }
}
