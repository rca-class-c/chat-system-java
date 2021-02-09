package client.interfaces;

public class MessageResponseDataFormat {
    int user;
    int message_id;

    public MessageResponseDataFormat(int user, int message_id) {
        this.user = user;
        this.message_id = message_id;
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
}
