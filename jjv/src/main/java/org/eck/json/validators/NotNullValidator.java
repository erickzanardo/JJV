package org.eck.json.validators;

import org.eck.json.JsonTypeValidator;
import org.eck.json.exception.JsonValidateException;

import com.google.gson.JsonElement;

public class NotNullValidator implements JsonTypeValidator {

    @Override
    public void validate(JsonElement element) {
        if(element == null || element.isJsonNull()) {
            throw new JsonValidateException("NotNull");
        }
    }
}
