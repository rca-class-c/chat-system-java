package server.repositories;

import server.config.Config;
import server.models.Group;
import server.models.GroupMember;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupMemberRepository {

    public GroupMember createMember(GroupMember group_member) throws SQLException {
        String sql ="insert into user_group (group_id, user_id) values(?,?)";
        Connection connection= Config.getConnection();

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
    public List<GroupMember> getAllMembers(int id) throws SQLException {
        List<GroupMember> memberList= new ArrayList<>();
        String sql= "select user_id from user_group where group_id=?";
        Connection connection= Config.getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,id);

        ResultSet resultSet= statement.executeQuery();

        while(resultSet.next()){
            int user_id = resultSet.getInt("user_id");

            GroupMember group_member= new GroupMember(id,user_id);
            memberList.add(group_member);
        }

        resultSet.close();
        statement.close();
        connection.close();
        return memberList;
    }

    public boolean deleteMember(GroupMember group_member) throws SQLException {
        String sql= "delete from user_group where group_id=? && user_id=?";

        Connection connection = Config.getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,group_member.getGroup_id());
        statement.setInt(2,group_member.getMember_id());

        boolean rowDeleted=statement.executeUpdate()>0;

        statement.close();
        connection.close();
        return rowDeleted;
    }

}
