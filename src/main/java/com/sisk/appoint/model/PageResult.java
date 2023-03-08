package com.sisk.appoint.model;

import java.util.List;

public class PageResult<T>{
    private List<T> data;
    private long totalElements;
    private long totalPages;
    private long size;
    private long pageNumber;
    private boolean first;

    private long numberOfElements;
    private boolean empty;
    private long offset;

    public PageResult(List<T> data, long totalElements) {
        this.data = data;
        this.totalElements = totalElements;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}
