package server.services;

import server.models.Group;
import server.repositories.GroupRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * Group services provider
 * @author: Yvette Gahamanyi
 */
public class GroupServices {
    private final GroupRepository groupRepository = new GroupRepository();

    public Group getGroupById(int id) throws SQLException {
        return groupRepository.getGroupById(id);
    }
    public List<Group> SearchGroups(String search_data) throws SQLException {
        return groupRepository.getUserSearchList(search_data);
    }
    public List<Group> getAllGroups() throws SQLException {
        return groupRepository.getAllGroups();
    }

    public Group createGroup(Group group) throws SQLException {
        return groupRepository.createGroup(group);
    }

    public Group updateGroup(Group group) throws SQLException {
        return groupRepository.updateGroup(group);
    }

    public boolean deleteGroup(int id) throws SQLException {
        return groupRepository.deleteGroup(id);
    }

}
