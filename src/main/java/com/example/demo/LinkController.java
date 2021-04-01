package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class LinkController {

    private final AtomicLong counter = new AtomicLong();

    @PostMapping("/shorten-link")
    Link addLink(@RequestBody Link link) {
        Link newLink = new Link(counter.incrementAndGet(), "test", fullLink, "test");
        newLink.setShortLink(newLink.generateShortLink());
		return newLink;
	}
}
