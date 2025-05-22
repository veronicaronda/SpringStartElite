package com.example.demo.dto;

import lombok.Data;

import java.util.List;
@Data
public class ResponsePageDTO<T> {
    private List<T> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;

    public ResponsePageDTO(){};

    public ResponsePageDTO(List<T> content,
                           int page,
                           int size,
                           long totalElements,
                           int totalPages,
                           boolean last) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }
}
