package org.eck.json;

import java.util.ArrayList;
import java.util.List;

import org.eck.json.exception.JsonValidateException;

import com.google.gson.JsonObject;

public class JsonValidator {
    private List<ValidatorEntry> validations = new ArrayList<>();

    public void addValidator(String field, JsonTypeValidator validator) {
        validations.add(new ValidatorEntry(field, validator));
    }

    public JsonValidationResult validate(JsonObject obj) {
        JsonValidationResult result = new JsonValidationResult();

        for (ValidatorEntry entry : validations) {
            String field = entry.getField();
            JsonTypeValidator validator = entry.getValidator();

            try {
                validator.validate(obj.get(field));
            } catch (JsonValidateException e) {
                result.addError(field, e.getError());
            }
        }

        return result;
    }

    class ValidatorEntry {
        private String field;
        private JsonTypeValidator validator;

        public ValidatorEntry(String field, JsonTypeValidator validator) {
            super();
            this.field = field;
            this.validator = validator;
        }

        public String getField() {
            return field;
        }

        public JsonTypeValidator getValidator() {
            return validator;
        }
    }
}
