package com.solarmax.lang.lang;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LangApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev-h2")
public class LangApplicationTests {

    public static final String TITLE = "title";
    public static final String DE = "de";
    public static final String TITLE_VALUE = "Titel";
    public static final String EN = "en";
    public static final String TITLE_VALUE_EN = "Title";
    public static final String SHOULD_CONTAINS_KEY = "Should contains key.";
    public static final String SHOULD_CONTAINS_CERTAIN_VALUE = "Should contains certain value";
    private String endpointURL;

    @Autowired
    private LangRepository langRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        this.endpointURL = "/lang-files/";
    }

    @Test
    public void testPositive() {
        Lang lang = new Lang();
        lang.setKey(TITLE);
        lang.setLang(DE);
        lang.setValue(TITLE_VALUE);

        Lang langEn = new Lang();
        langEn.setKey(TITLE);
        langEn.setLang(EN);
        langEn.setValue(TITLE_VALUE_EN);

        langRepository.save(lang);
        langRepository.save(langEn);

        Map<String, String> returnMap = restTemplate.getForObject(this.endpointURL + "de.json", Map.class);

        Assert.assertTrue(SHOULD_CONTAINS_KEY, returnMap.containsKey(TITLE));
        Assert.assertEquals(SHOULD_CONTAINS_CERTAIN_VALUE, returnMap.get(TITLE), TITLE_VALUE);

        Map<String, String> returnMapEn = restTemplate.getForObject(this.endpointURL + "en.json", Map.class);

        Assert.assertTrue(SHOULD_CONTAINS_KEY, returnMapEn.containsKey(TITLE));
        Assert.assertEquals(SHOULD_CONTAINS_CERTAIN_VALUE, returnMapEn.get(TITLE), TITLE_VALUE_EN);
    }

    @Test
    public void testNotFound() {
        ResponseEntity<?> responseEntity = restTemplate.getForEntity(this.endpointURL + "se.json", Map.class);
        Assert.assertEquals("Should be 404", HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}
