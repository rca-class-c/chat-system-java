package client.interfaces;

public class MessageResponseDataFormat {
    int user;
    int message_id;
    String content;

    public MessageResponseDataFormat(int user, int message_id) {
        this.user = user;
        this.message_id = message_id;
    }
    public MessageResponseDataFormat(int user, int message_id, String content){
        this.user = user;
        this.message_id = message_id;
        this.content = content;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
