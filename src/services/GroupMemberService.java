package services;

import models.Group_member;
import repositories.GroupMemberRepository;

import java.sql.SQLException;
import java.util.List;

public class GroupMemberService {
    private final GroupMemberRepository groupMemberRepository = new GroupMemberRepository();

    public boolean saveGroupMember(Group_member group_member) throws SQLException {
        return groupMemberRepository.createMember(group_member);
    }

    public List<Group_member> listGroupMembers(int id) throws SQLException {
        return groupMemberRepository.getAllMembers(id);
    }

    public void deleteMember(Group_member group_member) throws SQLException {
        groupMemberRepository.deleteMember(group_member);
    }
}
