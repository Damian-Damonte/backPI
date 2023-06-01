package com.dh.digitalbooking.dto;

import com.dh.digitalbooking.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductPageDto {
    private List<Product> content = new ArrayList<>();
    private int totalPages;
    private int currentPage;
    private Long totalElements;

    public ProductPageDto() {
    }

    public List<Product> getContent() {
        return content;
    }

    public void setContent(List<Product> content) {
        this.content = content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }
}
