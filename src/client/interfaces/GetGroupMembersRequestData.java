package client.interfaces;

public class GetGroupMembersRequestData {
    int group_id;

    public GetGroupMembersRequestData(int group_id) {
        this.group_id = group_id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }
}
