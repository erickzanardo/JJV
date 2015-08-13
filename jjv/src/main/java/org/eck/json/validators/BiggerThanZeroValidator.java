package org.eck.json.validators;

import org.eck.json.JsonTypeValidator;
import org.eck.json.exception.JsonValidateException;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class BiggerThanZeroValidator implements JsonTypeValidator {

    @Override
    public void validate(JsonElement element) {
        if(element != null && !element.isJsonNull()) {
            JsonPrimitive primitive = element.getAsJsonPrimitive();
            if(primitive.isNumber()) {
                double value = primitive.getAsDouble();
                if(value <= 0.0) {
                    throw new JsonValidateException("NumberMustBeBiggerThanZero");
                }
            } else {
                throw new JsonValidateException("InvalidNumber");
            }
        }
    }
}
