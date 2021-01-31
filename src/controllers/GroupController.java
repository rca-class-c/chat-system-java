package controllers;

import models.Group;
import services.GroupServices;
import services.UserService;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public class GroupController {
    private final GroupServices groupServices = new GroupServices();

    public Optional<Group> get(int id) throws SQLException {
        return this.groupServices.get(id);
    }

   public Collection<Group> getAll() throws SQLException {
        return this.groupServices.getAll();
    }

    public boolean create(Group group) throws SQLException {
        return this.groupServices.create(group);
    }

    public void delete(Group group) throws SQLException {
         this.groupServices.delete(group);
    }

    public void update(Group group) throws SQLException {
         this.groupServices.update(group);
    }

}
