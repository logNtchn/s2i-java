package com.solarmax.lang.lang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LangService {

    @Autowired
    private LangRepository langRepository;

    @Autowired
    private LangTransformer langTransformer;

    public Map<String, String> getLanguangeByFileName(String fileName) {
        return langTransformer.entitiesToPayload(langRepository.findByLang(fileName));
    }
}
