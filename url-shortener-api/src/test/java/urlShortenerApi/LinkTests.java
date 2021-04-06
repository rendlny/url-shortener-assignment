package urlShortenerApi;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.ZonedDateTime;

import urlShortenerApi.controller.LinkController;
import urlShortenerApi.model.Link;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jayway.jsonpath.internal.Path;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LinkTests {
    
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
        link.setShortLink("localhost/test");
        Assertions.assertTrue(link.getShortLink().equals("localhost/test"));

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
        String shortLink = link.generateShortLink();
        link.setShortLink(shortLink);
        Assertions.assertNotNull(link.getShortLink());
        Assertions.assertFalse(link.getShortLink().equals("localhost/test"));
        Assertions.assertTrue(link.getShortLink().contains("http://localhost:8080/"));
        Assertions.assertEquals(link.getShortLink(), shortLink);
    }

    @Test
	@DisplayName("Test should pass when json file for links is built")
	void shouldCreateJsonFile() throws IOException {
        //saveLinkToFile function should create the test json file
        LinkController linkController = new LinkController();
		Link link = new Link("www.test.com");
		linkController.saveLinkToFile(link, "link-test.json");

        //test file should now exist
        File file = new File("target/link-test.json");
        assertTrue(file.exists());

        //delete test file
        file.delete();

        //test file should no longer exist
        assertFalse(file.exists());
	}

	@Test
	@DisplayName("Test should pass when link is saved into file")
	void shouldSaveLinkToFile() throws JsonGenerationException, JsonMappingException, IOException {
        File file = new File("target/link-test.json");
        //make sure test file is empty or doesn't exist first
        
        LinkController linkController = new LinkController();
		Link link = new Link("www.test.com");
		linkController.saveLinkToFile(link, "link-test.json");
        assertTrue(file.length() > 0);

        //delete test file
        file.delete();
	}
    
}
