package com.course.server.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author JT
 */
public class Course {
    private String id;

    private String name;

    private String summary;

    private Integer time;

    private BigDecimal price;

    private String image;

    private String level;

    private String charge;

    private String status;

    private Integer enroll;

    private Integer sort;

    private Date createAt;

    private Date updateAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getEnroll() {
        return enroll;
    }

    public void setEnroll(Integer enroll) {
        this.enroll = enroll;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", summary=").append(summary);
        sb.append(", time=").append(time);
        sb.append(", price=").append(price);
        sb.append(", image=").append(image);
        sb.append(", level=").append(level);
        sb.append(", charge=").append(charge);
        sb.append(", status=").append(status);
        sb.append(", enroll=").append(enroll);
        sb.append(", sort=").append(sort);
        sb.append(", createAt=").append(createAt);
        sb.append(", updateAt=").append(updateAt);
        sb.append("]");
        return sb.toString();
    }
}