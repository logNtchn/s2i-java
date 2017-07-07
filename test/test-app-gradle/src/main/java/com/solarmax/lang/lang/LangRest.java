package com.solarmax.lang.lang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class LangRest {

    @Autowired
    private LangService langService;

    @RequestMapping(value = "/lang-files/{file}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Map<String, String>> produceFile(@PathVariable(value = "file") String file) {
        Map<String, String> retStructure = langService.getLanguangeByFileName(file);

        if (retStructure == null || retStructure.size() < 1) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(retStructure, HttpStatus.OK);
    }
}
