package server.services;

import client.interfaces.AddMemberRequestData;
import server.models.GroupMember;
import server.repositories.GroupMemberRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * this class creates, deletes and lists or group members in a certain group
 * author Phinah Mahoro
 */
public class GroupMemberService {
    private final GroupMemberRepository groupMemberRepository = new GroupMemberRepository();

    public GroupMember saveGroupMember(GroupMember group_member) throws SQLException {
        return groupMemberRepository.createMember(group_member);
    }

    public List<GroupMember> listGroupMembers(int id) throws SQLException {
        return groupMemberRepository.getAllMembers(id);
    }

    public boolean deleteMember(GroupMember group_member) throws SQLException {
        return groupMemberRepository.deleteMember(group_member);
    }
    public int[] createMembers(AddMemberRequestData newMembers) throws SQLException{
        return groupMemberRepository.createMembers(newMembers.getGroup_id(),newMembers.getUsers());
    }

}
