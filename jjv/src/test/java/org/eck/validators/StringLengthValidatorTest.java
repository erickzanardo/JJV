package org.eck.validators;

import org.eck.json.JsonValidationResult;
import org.eck.json.JsonValidator;
import org.eck.json.validators.StringLengthValidator;
import org.junit.Assert;
import org.junit.Test;

import com.google.gson.JsonObject;

public class StringLengthValidatorTest {

    @Test
    public void testValidate() {
        JsonValidator validator = new JsonValidator();
        validator.addValidator("someField", new StringLengthValidator(5));

        JsonObject obj = new JsonObject();
        obj.addProperty("someField", "asd");
        
        JsonValidationResult result = validator.validate(obj);
        Assert.assertEquals(true, result.hasErrors());
        Assert.assertEquals(1, result.fieldsInError().size());
        Assert.assertEquals("someField", result.fieldsInError().get(0));
        Assert.assertEquals("StringLength", result.getError("someField"));

        obj = new JsonObject();
        obj.addProperty("someField", "Some content");
        
        result = validator.validate(obj);
        Assert.assertEquals(false, result.hasErrors());
    }
}
