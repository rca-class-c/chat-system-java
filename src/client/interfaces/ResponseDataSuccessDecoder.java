package client.interfaces;

public class ResponseDataSuccessDecoder {
    String data;
    boolean success;

    public ResponseDataSuccessDecoder(String data, boolean success) {
        this.data = data;
        this.success = success;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
