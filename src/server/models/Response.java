package server.models;

public class Response {
    Object data;
    Boolean success;

    public Response(Object data, Boolean success) {
        this.data = data;
        this.success = success;
    }

    public Response(int data, String bad_request, String message) {
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
