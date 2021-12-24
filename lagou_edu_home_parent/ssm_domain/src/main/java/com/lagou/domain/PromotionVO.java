package com.lagou.domain;

/**
 * @author ersan
 * @date 2021/12/20
 */
public class PromotionVO {

    private Integer startPage;

    private Integer pageSize;

    public Integer getStartPage() {
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PromotionVO{" +
                "startPage=" + startPage +
                ", pageSize=" + pageSize +
                '}';
    }
}
