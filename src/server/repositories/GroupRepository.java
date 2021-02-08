package server.repositories;

import server.config.Config;
import server.models.Group;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Group queries repository
 * @author: Phinah Mahoro
 */
public class GroupRepository  {
    private List<Group> groupList= new ArrayList<>();
    public Group get(int id) throws SQLException {
        String sql = "select * from groups where group_id = ?";
        Connection connection= Config.getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,id);

        ResultSet resultSet= statement.executeQuery();

        if(resultSet.next()){
            int g_id = resultSet.getInt("group_id");
            String name = resultSet.getString("group_name");
            String description = resultSet.getString("description");
            int creator = resultSet.getInt("group_creator");
            java.sql.Date created_at = resultSet.getDate("created_at");
            java.sql.Date updated_at = resultSet.getDate("updated_at");

            return new Group(g_id,name,description,creator,created_at,updated_at);

        }
        resultSet.close();
        statement.close();
        connection.close();

        return null;
    }

    public List<Group> getAll() throws SQLException {
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

    public Group create(Group group) throws SQLException {
        String sql ="insert into groups (group_name, description,group_creator) values(?,?,?)";
        Connection connection= Config.getConnection();

        PreparedStatement statement= connection.prepareStatement(sql);
        statement.setString(1,group.getName());
        statement.setString(2,group.getName());
        statement.setInt(3,group.getCreator());

        boolean rowCreated= statement.executeUpdate()>0;
        statement.close();
        connection.close();

         if(rowCreated){
             return group;
         }
         return null;
    }

    public Group update(Group group) throws SQLException {
        String sql="update groups set name=?, description=?" +
                "where group_id=?";
        Connection connection=Config.getConnection();
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1,group.getName());
        statement.setString(2,group.getDescription());
        statement.setInt(3,group.getCreator());

        statement.close();
        connection.close();
        return group;
    }

    public void delete(Group group) throws SQLException {
        String sql="delete from groups where group_id=?";

        Connection connection= Config.getConnection();
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setInt(1,group.getId());

        statement.close();
        connection.close();
    }
    public List<Group> getUserSearchList(String search){
        try{
            Connection connection = Config.getConnection();
            Statement statement =  connection.createStatement();

            String query = String.format("SELECT * FROM groups where name = '%s' or description = '%s' ORDER BY group_id ASC;",search,search);
            ResultSet rs = statement.executeQuery(query);
            System.out.println("Reading users ....");
            List<Group> groups=new ArrayList<Group>();
            while(rs.next()){
                groups.add(new Group(rs.getInt("group_id"),rs.getString("name"),rs.getString("description"),
                        rs.getInt("group_creator")));
            }
            System.out.println(groups.size());
            return groups;
        }
        catch ( Exception e ) {

            System.err.println( e.getClass().getName()+": "+ e.getMessage() );

            System.exit(0);

        }
        return null;
    }
}
