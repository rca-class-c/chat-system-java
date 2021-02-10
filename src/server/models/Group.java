package server.models;

import java.sql.Date;

public class Group {
    private int id;
    private String name;
    private String description;
    private int creator;
    private java.sql.Date created_at;
    private java.sql.Date updated_at;

    public Group() {
    }



    public Group(int id,String name){
        this.id = id;
        this.name = name;
    }

    public Group(int id, String name, String description, int creator) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creator = creator;
    }

    public Group(int id, String name, String description, int creator, Date created_at, Date updated_at) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creator = creator;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Group(String name, String desctiption, int creator) {
        this.name = name;
        this.description = desctiption;
        this.creator = creator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
