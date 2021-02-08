package server.services;

import server.models.Group;
import server.models.User;
import server.repositories.GroupRepository;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Group services provider
 * @author: Yvette Gahamanyi
 */
public class GroupServices {
    private final GroupRepository groupRepository = new GroupRepository();

    public Optional<Group> get(int id) throws SQLException {
        return groupRepository.get(id);
    }
    public List<Group> SearchGroups(String search_data) throws SQLException {
        return groupRepository.getUserSearchList(search_data);
    }
    public Collection<Group> getAll() throws SQLException {
        return groupRepository.getAll();
    }

    public boolean create(Group group) throws SQLException {
        return groupRepository.create(group);
    }

    public void update(Group group) throws SQLException {
        groupRepository.update(group);
    }

    public void delete(Group group) throws SQLException {
        groupRepository.delete(group);
    }
}
