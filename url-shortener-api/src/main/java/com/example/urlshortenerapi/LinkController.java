package com.example.urlshortenerapi;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class LinkController {
    private final LinkRepository repository;

    private final LinkModelAssembler assembler;

    LinkController(LinkRepository repository, LinkModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    //CRUD Requests
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/links")
    CollectionModel<EntityModel<Link>> all() {

        List<EntityModel<Link>> links = repository.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(links, linkTo(methodOn(LinkController.class).all()).withSelfRel());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/links")
    EntityModel<Link> newLink(@RequestBody Link newLink) {
        //make sure generated code is unique
        String linkCode = generateUniqueCode(newLink);
        newLink.setCode(linkCode);
        //save to repo and return REST model
        repository.save(newLink);
        return assembler.toModel(newLink);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/links/{id}")
    EntityModel<Link> one(@PathVariable Long id) {
        Link link = repository.findById(id)
            .orElseThrow( () -> new LinkNotFoundException(id));

        return assembler.toModel(link);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/links/{id}")
    ResponseEntity<?> replaceLink(@RequestBody Link newLink, @PathVariable Long id) {
        
        Link updatedLink = repository.findById(id)
        .map(link -> {
            link.setName(newLink.getName());
            link.setLink(newLink.getLink());
            link.setCode(newLink.getCode());
            link.setClickCount(newLink.getClickCount());
            link.setCreatedAt(newLink.getCreatedAt());
            link.setEditedAt(newLink.getEditedAt());
            return repository.save(link);
        })
        .orElseGet(() -> {
            newLink.setId(id);
            return repository.save(newLink);
        });

        EntityModel<Link> entityModel = assembler.toModel(updatedLink);
        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
   
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/links/{id}")
    ResponseEntity<?> deleteLink(@PathVariable Long id) {
        repository.deleteById(id);
        //return HTTP 204 No Content response
        return ResponseEntity.noContent().build();
    }

    //Redirecting to full link
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{code}")
    RedirectView redirectTest(@PathVariable String code, RedirectAttributes attributes) {
        //find link via code
        Link link = repository.findByCode(code)
            .orElseThrow( () -> new LinkNotFoundException(code));
        
        //increase link's click count  
        link.setClickCount(link.getClickCount()+1);
        repository.save(link);

        //redirect to full link
        return new RedirectView(link.getLink());
    }

    //
    public String generateUniqueCode(Link link) {
        Boolean uniqueCode = false;

        while(uniqueCode == false){
            //generate a code
            link.generateCode();
            //check if code already exists in repo
            Link duplicateLink = repository.findByCode(link.getCode())
            .orElse(null);
            //if no duplicate is found, code is unique
            if(duplicateLink == null){
                uniqueCode = true;
            }
        }

        return link.getCode();
    }
    
}
