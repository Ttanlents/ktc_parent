package com.ktc.base.entity;

import java.util.List;

/**
 * @author 余俊锋
 * @date 2020/11/21 17:25
 * @Description :分页实体
 */
public class PageResult<T> {

    //总记录数
    private Long total;

    //当前页数据
    private List<T> rows;

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public PageResult() {
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
