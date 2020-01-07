package com.in28Minutes.rest.webservices.restfulwebserices.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import com.in28Minutes.rest.webservices.restfulwebserices.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Iterator;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id) {
        User user =  service.findOne(id);
        if(user==null) {
            throw new UserNotFoundException("id-" + id);
        }

        // HATEOAS
        Resource<User> resource = new Resource<>(user);

        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUer = service.saveUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUer.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user =  service.deleteById(id);
        if(user==null) {
            throw new UserNotFoundException("id-" + id);
        }
    }
}
