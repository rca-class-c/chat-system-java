package client.interfaces;

import java.util.List;

public class AddMemberRequestData {
    int group_id;
    List<Integer> users;

    public AddMemberRequestData(int group_id, List<Integer> users) {
        this.group_id = group_id;
        this.users = users;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public List<Integer> getUsers() {
        return users;
    }

    public void setUsers(List<Integer> users) {
        this.users = users;
    }
}
