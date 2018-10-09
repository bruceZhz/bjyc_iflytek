package com.binninfo.tobacco.entity;
import com.github.pagehelper.PageInfo;

import java.util.*;

public class DataGrid<T> {
    private Long total;
    private List<T> rows;

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

    public DataGrid(PageInfo<T> pageInfo){
        this.total = pageInfo.getTotal();
        this.rows = pageInfo.getList();
    }



}
