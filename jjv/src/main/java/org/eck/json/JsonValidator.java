package org.eck.json;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eck.json.exception.JsonValidateException;

import com.google.gson.JsonObject;

public class JsonValidator {
    private Map<String, JsonTypeValidator> validations = new HashMap<>();

    public void addValidator(String field, JsonTypeValidator validator) {
        validations.put(field, validator);
    }

    public JsonValidationResult validate(JsonObject obj) {
        JsonValidationResult result = new JsonValidationResult();

        Set<Entry<String, JsonTypeValidator>> entrySet = validations.entrySet();
        for(Entry<String, JsonTypeValidator> entry : entrySet) {
            String field = entry.getKey();
            JsonTypeValidator validator = entry.getValue();

            try {
                validator.validate(obj.get(field));
            } catch(JsonValidateException e) {
                result.addError(field, e.getError());
            }
        }

        return result;
    }
}
