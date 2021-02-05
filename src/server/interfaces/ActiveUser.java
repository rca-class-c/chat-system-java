package server.interfaces;

public class ActiveUser {
    int userID;
    String first_name;
    String last_name;

    public ActiveUser(int userID, String first_name, String last_name) {
        this.userID = userID;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
