# JJV (Java Json Validator)

Simple library to help validate JsonObjects in Java, JJV works above the google json library (GSON)

## Usage

```java
JsonValidator validator = new JsonValidator();
validator.addValidator("someField", new NotNullValidator());
validator.addValidator("someOtherField", new BiggerThanZeroValidator());
        
JsonObject obj = new JsonObject();
obj.addProperty("someField", JsonNull.INSTANCE);
obj.addProperty("someOtherField", 0);
        
result.hasErrors(); // true
result.fieldsInError().size(); // 2
result.fieldsInError().get(0); // someField
result.fieldsInError().get(1); // someOtherField
result.getError("someField"); // NotNull
result.getError("someOtherField"); // NumberMustBeBiggerThanZero
```

## Built-in validators

- BiggerThanZeroValidator
- NotEmptyStringValidator
- NotNullValidator
- StringLengthValidator

## Maven dependency

```xml
  <repository>
    <id>erickzanardo-releases</id>
    <url>http://erickzanardo.github.com/maven/releases/</url>
  </repository>

  <dependency>
    <groupId>org.eck.json</groupId>
    <artifactId>jjv</artifactId>
    <version>1.0</version>
  </dependency>
```
