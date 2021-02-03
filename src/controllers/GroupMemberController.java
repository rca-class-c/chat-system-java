package controllers;

import models.Group_member;
import services.GroupMemberService;

import java.sql.SQLException;
import java.util.List;

public class GroupMemberController {

    private final GroupMemberService groupMemberService=new GroupMemberService();
    public boolean CreateGroupMember(Group_member group_member) throws SQLException {
        return this.groupMemberService.saveGroupMember(group_member);
    }
    public List<Group_member> getAllGroupMembers(int id) throws SQLException {
        return this.groupMemberService.listGroupMembers(id);
    }
    public void deleteGroupMember(Group_member group_member) throws SQLException {
        this.groupMemberService.deleteMember(group_member);
    }
}
