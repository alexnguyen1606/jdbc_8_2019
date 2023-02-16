package com.laptrinhjavaweb.dto;

import java.io.Serializable;
import java.util.Date;

public class BaseDTO implements Serializable {
    private Long id;
    private Date createdDate;

    private String createdBy;

    private Date modifiedDate;

    private String modifiedBy;

    private Integer page;
    private Integer limit;
    private Long[] idDelete = new Long[]{};

    public Long[] getIdDelete() {
        return idDelete;
    }

    public void setIdDelete(Long[] idDelete) {
        this.idDelete = idDelete;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
