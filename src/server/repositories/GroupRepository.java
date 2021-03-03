package server.repositories;

import client.views.components.Component;
import server.config.PostegresConfig;
import server.models.Group;
import server.services.ReportsServices;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;


/**
 *
 * Group queries repository
 * @author: Gahamanyi yvette
 *
 */


public class GroupRepository  {
    private List<Group> groupList= new ArrayList<>();

    public Group getGroupById(int id) throws SQLException {
        String sql = "select * from groups where group_id = ?";
        Connection connection= PostegresConfig.getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,id);

        ResultSet resultSet= statement.executeQuery();

        if(resultSet.next()){
            int g_id = resultSet.getInt("group_id");
            String name = resultSet.getString("group_name");
            String description = resultSet.getString("description");
            int creator = resultSet.getInt("group_creator");
//            java.sql.Date created_at = resultSet.getDate("created_at");
//            java.sql.Date updated_at = resultSet.getDate("updated_at");

            return new Group(g_id,name,description,creator);

        }
        resultSet.close();
        statement.close();
        connection.close();

        return null;
    }

    public List<Group> getAllGroups() throws SQLException {
        String sql= "select * from groups";
        Connection connection= PostegresConfig.getConnection();

        Statement statement = connection.createStatement();
        ResultSet resultSet= statement.executeQuery(sql);

        while(resultSet.next()){
            int id = resultSet.getInt("group_id");
            String name = resultSet.getString("group_name");
            String description = resultSet.getString("description");
            int creator = resultSet.getInt("group_creator");

            Group group= new Group(id,name,description,creator);
            groupList.add(group);
        }

        resultSet.close();
        statement.close();
        connection.close();
        return groupList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    public Group getGroupsByUserId(int user_id) throws SQLException {
        String sql = "select group_id,groups.name from groups inner join user_group on user_group.user_id= ? where user_group.group_id=groups.id;";
        Connection connection= PostegresConfig.getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,user_id);

        ResultSet resultSet= statement.executeQuery();

        if(resultSet.next()){
            int g_id = resultSet.getInt("group_id");
            String name = resultSet.getString("group_name");

            return new Group(g_id,name);

        }
        resultSet.close();
        statement.close();
        connection.close();

        return null;
    }

    public Group createGroup(Group group) throws SQLException {
        String sql ="insert into groups (group_name, description,group_creator) values(?,?,?)";
        Connection connection= PostegresConfig.getConnection();

        PreparedStatement statement= connection.prepareStatement(sql);
        statement.setString(1,group.getName());
        statement.setString(2,group.getDescription());
        statement.setInt(3,group.getCreator());

        boolean rowCreated= statement.executeUpdate()>0;
        statement.close();
        connection.close();

         if(rowCreated){
             new ReportsServices().insertGroupReport();
             return group;

         }
         return null;
    }

    public Group updateGroup(Group group) throws SQLException {
        String sql="update groups set group_name=?, description=?" +
                "where group_id=?";
        Connection connection= PostegresConfig.getConnection();
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1,group.getName());
        statement.setString(2,group.getDescription());
        statement.setInt(3,group.getId());

        boolean rowUpdated = statement.executeUpdate()>0;
        statement.close();
        connection.close();

        return group;
    }

    public boolean deleteGroup(int id) throws SQLException {
        String sql="delete from groups where group_id=?";

        Connection connection= PostegresConfig.getConnection();
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setInt(1,id);

        boolean rowDeleted=statement.executeUpdate()>0;
        statement.close();
        connection.close();
        return rowDeleted;
    }

    public List<Group> getUserSearchList(String search){
        try{
            Connection connection = PostegresConfig.getConnection();
            Statement statement =  connection.createStatement();

            String query = String.format("SELECT * FROM groups where group_name = '%s' or description = '%s' ORDER BY group_id ASC;",search,search);
            ResultSet rs = statement.executeQuery(query);
            List<Group> groups=new ArrayList<Group>();
            while(rs.next()){
                groups.add(new Group(rs.getInt("group_id"),rs.getString("group_name"),rs.getString("description"),
                        rs.getInt("group_creator")));
            }

            return groups;
        }
        catch ( Exception e ) {
            Component.showErrorMessage(e.getMessage());
        }
        return null;
    }
}
