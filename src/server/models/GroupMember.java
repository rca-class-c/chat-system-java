package server.models;

public class GroupMember {
    private int group_id;
    private int member_id;

    public GroupMember() {
    }

    public GroupMember(int group_id, int member_id) {
        this.group_id = group_id;
        this.member_id = member_id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

}
