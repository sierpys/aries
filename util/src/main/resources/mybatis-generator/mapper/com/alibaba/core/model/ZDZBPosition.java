package com.alibaba.core.model;

import java.util.Date;

public class ZDZBPosition {
    private Long id;

    private String memberId;

    private Integer positionTotal;

    private Integer positionUsed;

    private String extension01;

    private String extension02;

    private String extension03;

    private Integer version;

    private Boolean isValid;

    private Date gmtCreate;

    private Date gmtModified;

    private String comment;

    public ZDZBPosition(Long id, String memberId, Integer positionTotal, Integer positionUsed, String extension01, String extension02, String extension03, Integer version, Boolean isValid, Date gmtCreate, Date gmtModified, String comment) {
        this.id = id;
        this.memberId = memberId;
        this.positionTotal = positionTotal;
        this.positionUsed = positionUsed;
        this.extension01 = extension01;
        this.extension02 = extension02;
        this.extension03 = extension03;
        this.version = version;
        this.isValid = isValid;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.comment = comment;
    }

    public ZDZBPosition() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    public Integer getPositionTotal() {
        return positionTotal;
    }

    public void setPositionTotal(Integer positionTotal) {
        this.positionTotal = positionTotal;
    }

    public Integer getPositionUsed() {
        return positionUsed;
    }

    public void setPositionUsed(Integer positionUsed) {
        this.positionUsed = positionUsed;
    }

    public String getExtension01() {
        return extension01;
    }

    public void setExtension01(String extension01) {
        this.extension01 = extension01 == null ? null : extension01.trim();
    }

    public String getExtension02() {
        return extension02;
    }

    public void setExtension02(String extension02) {
        this.extension02 = extension02 == null ? null : extension02.trim();
    }

    public String getExtension03() {
        return extension03;
    }

    public void setExtension03(String extension03) {
        this.extension03 = extension03 == null ? null : extension03.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}