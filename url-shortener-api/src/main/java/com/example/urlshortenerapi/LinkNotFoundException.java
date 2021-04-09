package com.example.urlshortenerapi;

public class LinkNotFoundException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    LinkNotFoundException(Long id) {
        super("Could not find link " + id);
    }

    LinkNotFoundException(String code) {
        super("Could not find link " + code);
    }
}
