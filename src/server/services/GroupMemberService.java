package server.services;

import server.models.GroupMember;
import server.repositories.GroupMemberRepository;

import java.sql.SQLException;
import java.util.List;

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
    public int[] createMembers(int group_id,List<GroupMember> groupMembers ) throws SQLException{
        return groupMemberRepository.createMembers(group_id,groupMembers);
    }

}
