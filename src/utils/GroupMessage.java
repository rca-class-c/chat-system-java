package utils;

public class GroupMessage {
    String content;
    Integer sender;
    Integer group_receiver;
    Integer original_message;

    public GroupMessage(String content, Integer sender, Integer group_receiver, Integer original_message) {
        this.content = content;
        this.sender = sender;
        this.group_receiver = group_receiver;
        this.original_message = original_message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSender() {
        return sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }

    public Integer getGroup_receiver() {
        return group_receiver;
    }

    public void setGroup_receiver(Integer group_receiver) {
        this.group_receiver = group_receiver;
    }

    public Integer getOriginal_message() {
        return original_message;
    }

    public void setOriginal_message(Integer original_message) {
        this.original_message = original_message;
    }
}
