package org.eck.json;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eck.json.exception.JsonValidateException;

import com.google.gson.JsonObject;

public class JsonValidator {
    private Map<String, Class<? extends JsonTypeValidator>> validations = new HashMap<>();
    private static Map<Class<? extends JsonTypeValidator>, JsonTypeValidator> validators;

    public void addValidator(String field, Class<? extends JsonTypeValidator> validator) {
        validations.put(field, validator);
    }

    public JsonValidationResult validate(JsonObject obj) {
        JsonValidationResult result = new JsonValidationResult();

        Set<Entry<String, Class<? extends JsonTypeValidator>>> entrySet = validations.entrySet();
        for(Entry<String, Class<? extends JsonTypeValidator>> entry : entrySet) {
            String field = entry.getKey();
            Class<? extends JsonTypeValidator> validatorClass = entry.getValue();

            JsonTypeValidator validator = validatorInstance(validatorClass);
            try {
                validator.validate(obj.get(field));
            } catch(JsonValidateException e) {
                result.addError(field, e.getError());
            }
        }

        return result;
    }

    private JsonTypeValidator validatorInstance(Class<? extends JsonTypeValidator> clazz) {
        if(JsonValidator.validators == null) {
            JsonValidator.validators = new HashMap<>();
        }

        if(JsonValidator.validators.get(clazz) == null) {
            try {
                JsonTypeValidator instance = clazz.newInstance();
                JsonValidator.validators.put(clazz, instance);
            } catch(Exception e) {
                throw new RuntimeException(e);
            }
        }
        return JsonValidator.validators.get(clazz);
    }
}
