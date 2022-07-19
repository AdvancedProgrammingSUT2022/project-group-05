package server;

import com.google.gson.Gson;

public class Response {
    private String message;

    public static Response convertFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Response.class);
    }

    public String convertToJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
