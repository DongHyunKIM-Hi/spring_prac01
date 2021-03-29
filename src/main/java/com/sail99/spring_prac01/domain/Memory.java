package com.sail99.spring_prac01.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Setter
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

    @Column(nullable = false)
    private int view;

    @Column(nullable = false)
    private Long userId;

    public Memory(String title,String nickname, String contents, Long userId) {
        this.userId = userId;
        this.title = title;
        this.nickname = nickname;
        this.contents = contents;
        this.view = 0;
    }
    public Memory(MemoryRequestDto requestDto, Long userId) {
        this.userId = userId;
        this.title = requestDto.getTitle();
        this.nickname = requestDto.getNickname();
        this.contents = requestDto.getContents();
        this.view = requestDto.getView();
    }
    public void update(MemoryRequestDto requestDto){
        this.contents = requestDto.getContents();
    }
    public  void hit(Memory memory){
        this.view = memory.getView() + 1;
    }
}
