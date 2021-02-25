package server.services;

import client.interfaces.AddMemberRequestData;
import server.models.GroupMember;
import server.models.User;
import server.repositories.GroupMemberRepository;

import java.sql.SQLException;
import java.util.List;

public class GroupMemberService {
    private final GroupMemberRepository groupMemberRepository = new GroupMemberRepository();

    public GroupMember saveGroupMember(GroupMember group_member) throws SQLException {
        return groupMemberRepository.createMember(group_member);
    }

    public List<User> listGroupMembers(int id) throws SQLException {
        return groupMemberRepository.getAllMembers(id);
    }

    public boolean deleteMember(GroupMember groupMember) throws SQLException {
        return groupMemberRepository.deleteMember(groupMember);
    }
    public int[] createMembers(AddMemberRequestData newMembers) throws SQLException{
        return groupMemberRepository.createMembers(newMembers);
    }

}
