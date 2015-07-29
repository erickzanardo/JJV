package org.eck.json;

import com.google.gson.JsonElement;

public interface JsonTypeValidator {
    public abstract void validate(JsonElement element); 
}
