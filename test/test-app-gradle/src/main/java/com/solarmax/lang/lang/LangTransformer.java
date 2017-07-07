package com.solarmax.lang.lang;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LangTransformer {

    public Map<String, String> entitiesToPayload(List<Lang> langList) {
        if (langList == null) return null;

        Map<String, String> returnMap = new HashMap<>();

        for (Lang lang : langList) {
            returnMap.put(lang.getKey(), lang.getValue());
        }

        return returnMap;
    }
}
