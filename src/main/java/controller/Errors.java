package controller;

public enum Errors{
    //MAP
    INVALID_DESTINATION("error: invalid destination"),
    //LOGIN
    USERNAME_NOT_FOUND("error: username not found");

    private final String error;

    Errors(String error) {
        this.error = error;
    }
}
