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

    public Group get(int id) throws SQLException {
        return groupRepository.get(id);
    }
    public List<Group> SearchGroups(String search_data) throws SQLException {
        return groupRepository.getUserSearchList(search_data);
    }
    public List<Group> getAll() throws SQLException {
        return groupRepository.getAll();
    }

    public Group create(Group group) throws SQLException {
        return groupRepository.create(group);
    }

    public Group update(Group group) throws SQLException {
        return groupRepository.update(group);
    }

    public void delete(Group group) throws SQLException {
        groupRepository.delete(group);
    }
}
