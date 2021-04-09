package com.example.urlshortenerapi;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.ZonedDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UrlShortenerApiApplicationTests {

	@Test
	void contextLoads() {

	}

	@Test
    @DisplayName("Should create link and get & set all variables")
    void shouldCreateLink(){
        Link link = new Link();
        
        //name
        link.setName("test name");
        Assertions.assertTrue(link.getName().equals("test name"));
        
        //link
        link.setLink("www.test.com");
        Assertions.assertTrue(link.getLink().equals("www.test.com"));

        //shortLink
        link.setCode("test");
        Assertions.assertTrue(link.getCode().equals("test"));

        //clickCount
        link.setClickCount(3);
        Assertions.assertTrue(link.getClickCount() == 3);

        //createdAt
        Assertions.assertNotNull(link.getCreatedAt());

        //editedAt
        Assertions.assertNotNull(link.getEditedAt());
        String newEditDate = ZonedDateTime.now().toString();
        link.setEditedAt(newEditDate);
        Assertions.assertEquals(link.getEditedAt(), newEditDate);

        //generateShortLink
        link.generateCode();
        Assertions.assertNotNull(link.getCode());
        Assertions.assertFalse(link.getCode().equals("test"));
    }

}
