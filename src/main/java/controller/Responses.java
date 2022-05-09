package controller;

public enum Responses{
    //MAP
    INVALID_DESTINATION("error: invalid destination"),
    //LOGIN
    USERNAME_NOT_FOUND("error: username not found");

    private final String response;

    Responses(String response) {
        this.response = response;
    }

    public String getResponse() {
        return this.response;
    }
}
