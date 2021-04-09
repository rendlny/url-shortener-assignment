package com.example.urlshortenerapi;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class LinkModelAssembler implements RepresentationModelAssembler<Link, EntityModel<Link>> {
    
    @Override
    public EntityModel<Link> toModel(Link link) {
        return EntityModel.of(link,
            linkTo(methodOn(LinkController.class).one(link.getId())).withSelfRel(),
            linkTo(methodOn(LinkController.class).all()).withRel("links")
        );
    }
}
