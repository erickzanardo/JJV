package org.eck.json;

import org.eck.json.validators.BiggerThanZeroValidator;
import org.eck.json.validators.NotNullValidator;
import org.junit.Assert;
import org.junit.Test;

import com.google.gson.JsonObject;

public class JsonValidatorTest {
    @Test
    public void testValidator() {
        JsonValidator validator = new JsonValidator();
        validator.addValidator("field", new NotNullValidator());
        validator.addValidator("field", new BiggerThanZeroValidator());

        JsonObject obj = new JsonObject();
        JsonValidationResult result = validator.validate(obj);
        Assert.assertTrue(result.hasErrors());
        Assert.assertEquals(1, result.fieldsInError().size());
        Assert.assertEquals(1, result.getErrors("field").size());

        obj = new JsonObject();
        obj.addProperty("field", 0);
        result = validator.validate(obj);
        Assert.assertTrue(result.hasErrors());
        Assert.assertEquals(1, result.fieldsInError().size());
        Assert.assertEquals(1, result.getErrors("field").size());
    }
}
