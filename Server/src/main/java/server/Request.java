package server;

import com.google.gson.Gson;

import java.util.HashMap;

public class Request {
    private String action;
    private HashMap<String, Object> params = new HashMap<>();

    public Request(String action) {
        this.action = action;
    }

    public void addParams(String key, Object value) {
        this.params.put(key, value);
    }

    public String convertToJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Request convertFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Request.class);
    }

    public HashMap<String, Object> getParams() {
        return params;
    }

    public String getAction() {
        return action;
    }
}
