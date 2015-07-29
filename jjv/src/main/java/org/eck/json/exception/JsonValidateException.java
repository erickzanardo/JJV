package org.eck.json.exception;

public class JsonValidateException extends RuntimeException {
    private static final long serialVersionUID = 1178943649140155697L;
    private String error;

    public JsonValidateException(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}

