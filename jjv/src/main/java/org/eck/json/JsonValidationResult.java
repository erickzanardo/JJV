package org.eck.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class JsonValidationResult {
    private Map<String, String> errors = new HashMap<String, String>();

    public void addError(String field, String error) {
        errors.put(field, error);
    }

    public String getError(String field) {
        return errors.get(field);
    }

    public List<String> fieldsInError() {
        List<String> result = new ArrayList<String>();
        Set<Entry<String, String>> entrySet = errors.entrySet();
        for(Entry<String, String> entry : entrySet) {
            result.add(entry.getKey());
        }
        return result;
    }

    public boolean hasErrors() {
        return errors.size() > 0;
    }
}
