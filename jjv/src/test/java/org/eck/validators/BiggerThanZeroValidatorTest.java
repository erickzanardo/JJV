package org.eck.validators;

import org.eck.json.JsonValidationResult;
import org.eck.json.JsonValidator;
import org.eck.json.validators.BiggerThanZeroValidator;
import org.junit.Assert;
import org.junit.Test;

import com.google.gson.JsonObject;

public class BiggerThanZeroValidatorTest {
    @Test
    public void testValidate() {
        JsonValidator validator = new JsonValidator();
        validator.addValidator("someField", new BiggerThanZeroValidator());

        JsonObject obj = new JsonObject();

        JsonValidationResult result = validator.validate(obj);
        Assert.assertEquals(false, result.hasErrors());

        obj = new JsonObject();
        obj.addProperty("someField", "adsad");

        result = validator.validate(obj);
        Assert.assertEquals(true, result.hasErrors());
        Assert.assertEquals(1, result.fieldsInError().size());
        Assert.assertEquals("someField", result.fieldsInError().get(0));
        Assert.assertEquals("InvalidNumber", result.getError("someField"));

        obj = new JsonObject();
        obj.addProperty("someField", 0);

        result = validator.validate(obj);
        Assert.assertEquals(true, result.hasErrors());
        Assert.assertEquals(1, result.fieldsInError().size());
        Assert.assertEquals("someField", result.fieldsInError().get(0));
        Assert.assertEquals("NumberMustBeBiggerThanZero", result.getError("someField"));

        obj = new JsonObject();
        obj.addProperty("someField", 1);

        result = validator.validate(obj);
        Assert.assertEquals(false, result.hasErrors());
    }
}
