package client.interfaces;

import server.models.User;

import java.util.List;

public class UsersList {
    User[] users;
    List<Integer> ids;

    public UsersList(User[] users, List<Integer> ids) {
        this.users = users;
        this.ids = ids;
    }

    public User[] getUsers() {
        return users;
    }

    public List<Integer> getIds() {
        return ids;
    }
}
