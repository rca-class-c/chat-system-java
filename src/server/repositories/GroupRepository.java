package server.repositories;

import server.config.Config;
import server.models.Group;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;

public class GroupRepository  {
    private List<Group> groupList= new ArrayList<>();
    public Optional<Group> get(int id) throws SQLException {
        Group group=null;
        String sql = "select * from groups where group_id = ?";
        Connection connection= Config.getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,id);

        ResultSet resultSet= statement.executeQuery();

        if(resultSet.next()){
            String name = resultSet.getString("group_name");
            String description = resultSet.getString("description");
            int creator = resultSet.getInt("group_creator");
            Date created_at = resultSet.getDate("created_at");
            Date updated_at = resultSet.getDate("updated_at");

            group= new Group();
        }
        resultSet.close();
        statement.close();
        connection.close();

        return Optional.ofNullable(group);
    }

    public Collection<Group> getAll() throws SQLException {
        String sql= "select * from group";
        Connection connection= Config.getConnection();

        Statement statement = connection.createStatement();
        ResultSet resultSet= statement.executeQuery(sql);

        while(resultSet.next()){
            String name = resultSet.getString("group_name");
            String description = resultSet.getString("description");
            int creator = resultSet.getInt("group_creator");

            Group group= new Group(name,description,creator);
            groupList.add(group);
        }

        resultSet.close();
        statement.close();
        connection.close();
        return groupList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    public boolean create(Group group) throws SQLException {
        String sql ="insert into groups (group_name, description,group_creator) values(?,?,?)";
        Connection connection= Config.getConnection();

        PreparedStatement statement= connection.prepareStatement(sql);
        statement.setString(1,group.getName());
        statement.setString(2,group.getName());
        statement.setInt(3,group.getCreator());

        boolean rowCreated= statement.executeUpdate()>0;
        statement.close();
        connection.close();

        return rowCreated;
    }

    public void update(Group group) throws SQLException {
        String sql="update groups set name=?, description=?" +
                "where group_id=?";
        Connection connection=Config.getConnection();
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1,group.getName());
        statement.setString(2,group.getDesctiption());
        statement.setInt(3,group.getCreator());

        statement.close();
        connection.close();
    }

    public void delete(Group group) throws SQLException {
        String sql="delete from groups where group_id=?";

        Connection connection= Config.getConnection();
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setInt(1,group.getId());

        statement.close();
        connection.close();
    }
}
