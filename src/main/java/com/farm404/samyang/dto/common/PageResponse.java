package com.farm404.samyang.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    private List<T> content;
    private int currentPage;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean first;
    private boolean last;
    
    public static <T> PageResponse<T> of(List<T> content, PageRequest pageRequest, long totalElements) {
        int totalPages = (int) Math.ceil((double) totalElements / pageRequest.getSize());
        return PageResponse.<T>builder()
                .content(content)
                .currentPage(pageRequest.getPage())
                .pageSize(pageRequest.getSize())
                .totalElements(totalElements)
                .totalPages(totalPages)
                .first(pageRequest.getPage() == 1)
                .last(pageRequest.getPage() >= totalPages)
                .build();
    }
}