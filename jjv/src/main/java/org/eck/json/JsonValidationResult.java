package org.eck.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class JsonValidationResult {
    private Map<String, List<String>> errors = new HashMap<String, List<String>>();

    public void addError(String field, String error) {
        if(errors.get(field) == null) errors.put(field, new ArrayList<String>());
        errors.get(field).add(error);
    }

    public String getError(String field) {
        if(errors.get(field) != null) return errors.get(field).get(0);
        return null;
    }

    public List<String> getErrors(String field) {
        return errors.get(field);
    }

    public List<String> fieldsInError() {
        List<String> result = new ArrayList<String>();
        Set<Entry<String, List<String>>> entrySet = errors.entrySet();
        for(Entry<String, List<String>> entry : entrySet) {
            result.add(entry.getKey());
        }
        return result;
    }

    public boolean hasErrors() {
        return errors.size() > 0;
    }
}
