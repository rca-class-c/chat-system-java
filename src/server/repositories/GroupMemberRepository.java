package server.repositories;

import server.config.PostegresConfig;
import server.models.GroupMember;
import server.models.User;
import server.services.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
/**
 * @Author: Gahamanyi Yvette
 * */



public class GroupMemberRepository {

    public GroupMember createMember(GroupMember group_member) throws SQLException {
        String sql ="insert into user_group (group_id, user_id) values(?,?)";
        Connection connection= PostegresConfig.getConnection();

        PreparedStatement statement= connection.prepareStatement(sql);
        statement.setInt(1,group_member.getGroup_id());
        statement.setInt(2,group_member.getMember_id());

        boolean rowCreated= statement.executeUpdate()>0;
        statement.close();
        connection.close();

        if(rowCreated){
            return group_member;
        }
        return null;
    }

    public int[] createMembers(List<GroupMember> groupMembers) throws SQLException {
        String sql ="insert into user_group (group_id, user_id) values(?,?)";
        Connection connection= PostegresConfig.getConnection();

        PreparedStatement statement= connection.prepareStatement(sql);

        for (Iterator<GroupMember> iterator = groupMembers.iterator(); iterator.hasNext();){
            GroupMember groupMember= iterator.next();
            statement.setInt(1,groupMember.getGroup_id());
            statement.setInt(2,groupMember.getGroup_id());
            statement.addBatch();
        }

        int[] updatedCounts = statement.executeBatch();



        statement.close();
        connection.close();

        if(updatedCounts != null ){
            return updatedCounts;
        }
        return null;
    }



    public List<User> getAllMembers(int id) throws SQLException {
        List<User> memberList= new ArrayList<>();
        String sql= "select user_id from user_group where group_id=?";
        Connection connection= PostegresConfig.getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,id);

        ResultSet resultSet= statement.executeQuery();

        while(resultSet.next()){
            int user_id = resultSet.getInt("user_id");

            //GroupMember group_member= new GroupMember(id,user_id);
            User user  =    new UserService().getUserById(user_id);
            if(user != null){
                memberList.add(user);
            }
        }

        resultSet.close();
        statement.close();
        connection.close();
        return memberList;
    }

    public boolean deleteMember(GroupMember groupMember) throws SQLException {
        String sql= "delete from user_group where group_id=? && user_id=?";

        Connection connection = PostegresConfig.getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,groupMember.getGroup_id());
        statement.setInt(2,groupMember.getMember_id());

        boolean rowDeleted=statement.executeUpdate()>0;

        statement.close();
        connection.close();
        return rowDeleted;
    }

    public int[] createMembers(int group_id,Integer[] groupMembers) throws SQLException {
        String sql ="insert into user_group (group_id, user_id) values(?,?)";
        Connection connection= PostegresConfig.getConnection();
        PreparedStatement statement= connection.prepareStatement(sql);
        for(Integer user : groupMembers){
            statement.setInt(1,group_id);
            statement.setInt(2,user);
            statement.addBatch();
        }
        int[] updatedCounts = statement.executeBatch();

        statement.close();
        connection.close();
        if(updatedCounts != null ){
            return updatedCounts;
        }
        return null;
    }
}
