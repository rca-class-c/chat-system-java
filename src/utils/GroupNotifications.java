package utils;

public class GroupNotifications {
    private int group_id;
    private int message_count;

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getMessage_count() {
        return message_count;
    }

    public void setMessage_count(int message_count) {
        this.message_count = message_count;
    }
public GroupNotifications(){

}
    public GroupNotifications(int group_id, int message_count) {
        this.group_id = group_id;
        this.message_count = message_count;
    }
}
