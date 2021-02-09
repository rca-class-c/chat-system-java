package client.interfaces;

public class Request {
    Object data;
    String request_type;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getRequest_type() {
        return request_type;
    }

    public void setRequest_type(String request_type) {
        this.request_type = request_type;
    }

    public Request(Object data, String request_type) {
        this.data = data;
        this.request_type = request_type;
    }
}
