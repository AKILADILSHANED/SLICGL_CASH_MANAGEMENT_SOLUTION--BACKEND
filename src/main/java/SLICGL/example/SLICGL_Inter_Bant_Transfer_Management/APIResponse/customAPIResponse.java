package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse;

public class customAPIResponse<T> {
    private boolean success;
    private String message;
    private T responseObject;

    public customAPIResponse() {
    }

    public customAPIResponse(boolean success, String message, T responseObject) {
        this.success = success;
        this.message = message;
        this.responseObject = responseObject;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(T responseObject) {
        this.responseObject = responseObject;
    }
}
