package server.repositories;

import server.config.PostegresConfig;
import server.models.Permission;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @Description: this is a class that represents the crud(create, read, update and delete)
 * in the Permission entity
 * @author: Gahamanyi yvette
 * @date: 2-4-2021
 *
 * */

public class PermissionRepository {

    List<Permission> permissionList=new ArrayList<>();

    public Permission getPermission(int id) throws SQLException {
        Permission permission=null;
        String sql="select * from Permissions where permission_id = ?";
        Connection connection= PostegresConfig.getConnection();

        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setInt(1,id);

        ResultSet resultSet= statement.executeQuery();
        if(resultSet.next()){
            String name=resultSet.getString("name");
            String status= resultSet.getString("status");
            Date created_at= resultSet.getDate("created_at");
            Date updated_at= resultSet.getDate("updated_at");

            permission=new Permission(id,name,status,created_at,updated_at);

        }
        resultSet.close();
        statement.close();
        connection.close();

        return permission;
    }

    public List<Permission> getAllPermission() throws SQLException {
        String sql="select * from Permissions ";
        Connection connection= PostegresConfig.getConnection();

        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sql);

        while (resultSet.next()){
            int id= resultSet.getInt("permission_id");
            String name= resultSet.getString("name");
            String status= resultSet.getString("status");
            Date created_at = resultSet.getDate("created_at");
            Date updated_at = resultSet.getDate("updated_at");

            Permission permission= new Permission(id,name,status,created_at,updated_at);
            permissionList.add(permission);
        }

        resultSet.close();
        statement.close();
        connection.close();

        return permissionList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));

    }

    public boolean createPermission(Permission permission) throws SQLException {
        String sql ="insert into Permissions(name,status) values (?,?)";
        Connection connection= PostegresConfig.getConnection();

        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1,permission.getPermission_name());
        statement.setString(2,permission.getPermission_status());

        boolean rowCreated= statement.executeUpdate()>0;
        statement.close();
        connection.close();

        return rowCreated;
    }

    public boolean deletePermission(Permission permission) throws SQLException {
        String sql="delete from permissions where id=?";
        Connection connection= PostegresConfig.getConnection();

        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setInt(1,permission.getPermission_id());

        boolean rowDeleted= statement.executeUpdate()>0;
        statement.close();
        connection.close();

        return rowDeleted;
    }

    public  boolean updatePermission(Permission permission) throws SQLException {
        String sql="update permissions set name=? where permission_id=?";
        Connection connection= PostegresConfig.getConnection();

        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1,permission.getPermission_name());
        statement.setInt(2,permission.getPermission_id());

        boolean rowUpdated=statement.executeUpdate()>0;
        statement.close();
        connection.close();

        return rowUpdated;
    }

    public boolean changeStatus(int id) throws SQLException {
        String sql="update permissions set status=? where permission_id=?";
        Connection connection= PostegresConfig.getConnection();

        PreparedStatement statement=connection.prepareStatement(sql);
        //statement.setString(1,permission.getPermission_name());
        statement.setInt(2,id);

        boolean rowUpdated=statement.executeUpdate()>0;
        statement.close();
        connection.close();

        return rowUpdated;
    }

}
