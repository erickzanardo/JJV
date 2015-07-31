package org.eck.json.validators;

import org.eck.json.JsonTypeValidator;
import org.eck.json.exception.JsonValidateException;

import com.google.gson.JsonElement;

public class StringLengthValidator implements JsonTypeValidator {

    private int length;

    public StringLengthValidator(int length) {
        this.length = length;
    }

    @Override
    public void validate(JsonElement element) {
        if(element != null && !element.isJsonNull()) {
            String value = element.getAsString();
            if(value.length() < length) {
                throw new JsonValidateException("StringLength");
            }
        }
    }
}
