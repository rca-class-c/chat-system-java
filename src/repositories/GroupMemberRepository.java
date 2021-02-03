package repositories;

import models.Group_member;

import config.Config;

import java.io.ObjectInputFilter;
import java.sql.*;
import java.util.*;

public class GroupMemberRepository {
    public boolean createMember(Group_member group_member) throws SQLException {
        String sql ="insert into user_group (group_id, user_id) values(?,?)";
        Connection connection= Config.getConnection();

        PreparedStatement statement= connection.prepareStatement(sql);
        statement.setInt(1,group_member.getGroup_id());
        statement.setInt(2,group_member.getMember_id());

        boolean rowCreated= statement.executeUpdate()>0;
        statement.close();
        connection.close();

        return rowCreated;
    }
    public List<Group_member> getAllMembers(int id) throws SQLException {
        List<Group_member> memberList= new ArrayList<>();
        String sql= "select user_id from user_group where group_id=?";
        Connection connection= Config.getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,id);

        ResultSet resultSet= statement.executeQuery();

        while(resultSet.next()){
            int user_id = resultSet.getInt("user_id");

            Group_member group_member= new Group_member(id,user_id);
            memberList.add(group_member);
        }

        resultSet.close();
        statement.close();
        connection.close();
        return memberList;
    }

    public void deleteMember(Group_member group_member) throws SQLException {
        String sql= "delete from user_group where group_id=? && user_id=?";

        Connection connection = Config.getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,group_member.getGroup_id());
        statement.setInt(2,group_member.getMember_id());

        statement.close();
        connection.close();
    }
}
