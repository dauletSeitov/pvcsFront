package tools;

public class Response<T> {
    private T response;
    private boolean responseState;

    public Response(T response, boolean responseState) {
        this.response = response;
        this.responseState = responseState;
    }

    public T getResponse() {
        return response;
    }
    
    public boolean getResponseState(){
        return responseState;
    }

    @Override
    public String toString() {
        return "Response{" +
                "response=" + response +
                ", responseState=" + responseState +
                '}';
    }
}