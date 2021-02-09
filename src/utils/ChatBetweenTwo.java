package utils;

public class ChatBetweenTwo {
    int firstUser;
    int lastUser;

    public ChatBetweenTwo(int firstUser, int lastUser) {
        this.firstUser = firstUser;
        this.lastUser = lastUser;
    }

    public int getFirstUser() {
        return firstUser;
    }

    public void setFirstUser(int firstUser) {
        this.firstUser = firstUser;
    }

    public int getLastUser() {
        return lastUser;
    }

    public void setLastUser(int lastUser) {
        this.lastUser = lastUser;
    }
}
