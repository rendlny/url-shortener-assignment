package com.example.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@RestController
public class LinkController {

    private final AtomicLong counter = new AtomicLong();

    @PostMapping("/shorten-link")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Link addLink(@RequestBody Link link) throws JsonMappingException, JsonProcessingException {
        String shortLink = link.generateShortLink();
        //if link not unique, loop until unique short-link generated
        //while(checkLinkIsUnique(shortLink) == false){
           shortLink = link.generateShortLink();
        //}
        Link newLink = new Link(counter.incrementAndGet(), link.getName(), link.getLink(), shortLink);
        try {
            saveLinkToFile(newLink);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return newLink;
	}

    @GetMapping("/links")
    public String getLinks() throws FileNotFoundException, JsonMappingException, JsonProcessingException {
        File file = new File("target/link.json");
        String jsonFileContent;
        if(file.exists()){
            Scanner scanner = new Scanner(file).useDelimiter("\\Z");
            jsonFileContent = scanner.next();
            scanner.close();
            //final ObjectMapper objectMapper = new ObjectMapper();
            //Link[] links = objectMapper.readValue(jsonFileContent, Link[].class);
        }else{
            jsonFileContent = "No links";
        }
        
        return jsonFileContent;
    }

    public Link saveLinkToFile(Link link) throws JsonGenerationException, JsonMappingException, IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("target/link.json");
        String jsonFormatting = null;
        
        if(file.length() == 0){ // empty file, add starting square bracket
            jsonFormatting = "[";
        }else{ //JSON already in file, add comma to separate JSON objects
            jsonFormatting = ",";
            //remove current closing bracket by removing final character from file
            Scanner scanner = new Scanner(file).useDelimiter("\\Z");
            String jsonFileContent = scanner.next();
            scanner.close();
            String withoutLastCharacter = jsonFileContent.substring(0, jsonFileContent.length() - 1);
            FileWriter myWriter = new FileWriter("target/link.json");
            myWriter.write(withoutLastCharacter);
            myWriter.close();
        }
        OutputStream os = new FileOutputStream(file, true);
        //convert object to JSON string with json formatter
        String jsonInString = jsonFormatting + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(link);
        //write json to file
        os.write(jsonInString.getBytes(), 0, jsonInString.length());
        //close square bracket
        os.write("]".getBytes(), 0, "]".length());
        os.close();
        return link;
    }

    public boolean checkLinkIsUnique(String shortLink) throws JsonMappingException, JsonProcessingException{
        Boolean result = true;
        //pull Link objects from links.json
        File file = new File("target/link.json");
        String jsonFromFile;
        try {
            Scanner scanner = new Scanner(file).useDelimiter("\\Z");
            jsonFromFile = scanner.next();
            scanner.close();
            final ObjectMapper objectMapper = new ObjectMapper();
            Link[] links = objectMapper.readValue(jsonFromFile, Link[].class);
            
            //loop through existing links to check this short-link doesn't match
            for (Link link : links) {
                if(link.getShortLink() == shortLink){
                    result = false;
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }
}
