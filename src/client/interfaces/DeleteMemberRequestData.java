package client.interfaces;

public class DeleteMemberRequestData {
    int group_id;
    int user_id;

    public DeleteMemberRequestData(int group_id, int user_id) {
        this.group_id = group_id;
        this.user_id = user_id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
