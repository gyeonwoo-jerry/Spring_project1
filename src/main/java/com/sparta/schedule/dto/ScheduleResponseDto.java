package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String username;
    private String title;
    private String contents;
    private LocalDateTime initialDate;
    private LocalDateTime updateDate;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.username = schedule.getUsername();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.initialDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    public ScheduleResponseDto(Long id, String username, String contents, String title, LocalDateTime initialdate, LocalDateTime update) {
        this.id = id;
        this.username = username;
        this.contents = contents;
        this.title = title;
        this.initialDate = initialdate;
        this.updateDate = update;
    }
}
