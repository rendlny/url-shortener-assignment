package com.example.urlshortenerapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase (LinkRepository repository) {
        //create Link entities and store them in repo
        return args -> {
            log.info("Preloading " + repository.save(new Link("https://www.google.com")));
            log.info("Preloading " + repository.save(new Link("https://www.google.com/search?q=cute+cats+in+costumes&sclient=img")));
        };
    }
}
