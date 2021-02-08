package server.models;

import java.sql.Date;
/**
 *
 * @description: this is a model that represents Permissions entity or table
 * @author: Gahamanyi yvette
 * @date: 4-2-2021
 *
 * */

public class Permission {
    private int permission_id;
    private String permission_name;
    private String permission_status;
    private Date permission_created_at;
    private Date permission_updated_at;

    public Permission() {
    }

    public Permission(int permission_id, String permission_name, String permission_status, Date permission_created_at, Date permission_updated_at) {
        this.permission_id = permission_id;
        this.permission_name = permission_name;
        this.permission_status = permission_status;
        this.permission_created_at = permission_created_at;
        this.permission_updated_at = permission_updated_at;
    }

    public Permission(String permission_name, String permission_status) {
        this.permission_name = permission_name;
        this.permission_status = permission_status;
    }

    public int getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(int permission_id) {
        this.permission_id = permission_id;
    }

    public String getPermission_name() {
        return permission_name;
    }

    public void setPermission_name(String permission_name) {
        this.permission_name = permission_name;
    }

    public String getPermission_status() {
        return permission_status;
    }

    public void setPermission_status(String permission_status) {
        this.permission_status = permission_status;
    }

    public Date getPermission_created_at() {
        return permission_created_at;
    }

    public void setPermission_created_at(Date permission_created_at) {
        this.permission_created_at = permission_created_at;
    }

    public Date getPermission_updated_at() {
        return permission_updated_at;
    }

    public void setPermission_updated_at(Date permission_updated_at) {
        this.permission_updated_at = permission_updated_at;
    }
}

