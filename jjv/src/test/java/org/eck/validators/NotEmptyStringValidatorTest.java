package org.eck.validators;

import org.eck.json.JsonValidationResult;
import org.eck.json.JsonValidator;
import org.eck.json.validators.NotEmptyStringValidator;
import org.junit.Assert;
import org.junit.Test;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

public class NotEmptyStringValidatorTest {

    @Test
    public void testValidate() {
        JsonValidator validator = new JsonValidator();
        validator.addValidator("someField", new NotEmptyStringValidator());

        JsonObject obj = new JsonObject();
        
        JsonValidationResult result = validator.validate(obj);
        Assert.assertEquals(true, result.hasErrors());
        Assert.assertEquals(1, result.fieldsInError().size());
        Assert.assertEquals("someField", result.fieldsInError().get(0));
        Assert.assertEquals("EmptyString", result.getError("someField"));

        obj = new JsonObject();
        obj.addProperty("someField", "");
        
        result = validator.validate(obj);
        Assert.assertEquals(true, result.hasErrors());

        obj = new JsonObject();
        obj.add("someField", JsonNull.INSTANCE);
        
        result = validator.validate(obj);
        Assert.assertEquals(true, result.hasErrors());

        obj = new JsonObject();
        obj.addProperty("someField", " ");
        
        result = validator.validate(obj);
        Assert.assertEquals(true, result.hasErrors());

        obj = new JsonObject();
        obj.addProperty("someField", "Some content");
        
        result = validator.validate(obj);
        Assert.assertEquals(false, result.hasErrors());
    }
}
