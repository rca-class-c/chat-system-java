package server.models;

import java.sql.Timestamp;
import java.sql.Date;

// Created by Keny
public class categories {
    private int categoryId;
    private  String categoryName;
    private Date created_at;
    private Date updated_at;
    public categories(){

    }
    categories(int id, String name,Date date){
        this.categoryId=id;
        this.categoryName=name;
        this.created_at=date;
    }
    public categories(String name, Date created_at,Date updated_at ){
        this.categoryName=name;
        this.created_at=created_at;
        this.updated_at=updated_at;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }
}
