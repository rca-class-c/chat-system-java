package client.interfaces;

public class ProfileRequestData {
    int id;

    public ProfileRequestData(int id) {
        this.id = id;
    }

    public int getData() {
        return id;
    }

    public void setData(int id) {
        this.id = id;
    }
}
