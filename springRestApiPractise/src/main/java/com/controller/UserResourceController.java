package com.controller;

import com.domain.User;
import com.exception.UserNotFoundException;
import com.service.UserDaoService;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.ws.rs.QueryParam;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
public class UserResourceController {

    @Autowired
    UserDaoService userDaoService;

    @GetMapping("/users")
    public List<User>  retrieveAllUsers(){
        return userDaoService.findAll();
    }

    @GetMapping("users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id) {
        System.out.println(String.format(" Requested for user id %s", id));
        User user =  userDaoService.findOne(id);
        if(user==null) {
            throw new UserNotFoundException("id-" + id);
        }

        // HATEOAS
        Resource<User> resource = new Resource<>(user);

        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));
        new ResponseEntity(resource, HttpStatus.OK);
        return resource;
    }

    @GetMapping("user/{id}")
    public ResponseEntity<Object> retrieveUser2(@PathVariable int id) {
        System.out.println(String.format(" Requested for user id %s", id));
        User user =  userDaoService.findOne(id);
        if(user==null) {
            throw new UserNotFoundException("id-" + id);
        }

        // HATEOAS
        Resource<User> resource = new Resource<>(user);

        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));
        return new ResponseEntity(resource, HttpStatus.OK);
    }


    @GetMapping("searchUser")
    public ResponseEntity<Object> searchUser(@QueryParam("id") int id, @QueryParam("name") String name) {
        System.out.println(String.format(" Requested for user id %s", id));
        User user =  userDaoService.searchUser(id, name);
        if(user==null) {
            throw new UserNotFoundException(String.format("User not found for %s id and %s name", id, name));
        }

        // HATEOAS
        Resource<User> resource = new Resource<>(user);

        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));
        return new ResponseEntity(resource, HttpStatus.OK);
    }

    //Difference between query param and request param is query param is dependency of javax.ws.rs and requestparam is dependency is of spring framework and
    //It's not mendatory to pass all query param but in requestparam need to pass all request param util you mention as required false.

    @GetMapping("searchUserByRequestparam")
    public ResponseEntity<Object> searchUserByRequestParam(@RequestParam("id") int id, @RequestParam("name") String name) {
        System.out.println(String.format(" Requested for user id %s", id));
        User user =  userDaoService.searchUser(id, name);
        if(user==null) {
            throw new UserNotFoundException(String.format("User not found for id : %s id and name : %s", id, name));
        }

        // HATEOAS
        Resource<User> resource = new Resource<>(user);

        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));
        return new ResponseEntity(resource, HttpStatus.OK);
    }


    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUer = userDaoService.saveUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUer.getId())
                .toUri();

        //        return new ResponseEntity(location,HttpStatus.CREATED);
        return ResponseEntity.created(location).build(); // with this type location will be header as well.
    }

    @PutMapping("/users")
    public ResponseEntity<Object> updateUser(@Valid @RequestBody User user) {
        User savedUer = userDaoService.updateUser(user);

        return ResponseEntity.ok(savedUer); // with this type location will be header as well.
    }

    @PatchMapping("/users")
    public ResponseEntity<Object> patchUser(@Valid @RequestBody User user) {
        User patchedUser = userDaoService.patchUser(user);
        return ResponseEntity.ok(patchedUser); // with this type location will be header as well.
    }

    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user =  userDaoService.deleteById(id);
        if(user==null) {
            throw new UserNotFoundException("id-" + id);
        }
    }

    @GetMapping("/goodMorningMessage")
    public ResponseEntity<String> readMessage(@RequestHeader("Accept-Language") Locale locale) {

        String msg =  userDaoService.readMessage(locale);
        return new ResponseEntity<String>(msg,HttpStatus.OK);
    }

    @GetMapping("/goodMorningMessage2")
    public ResponseEntity<String> readMessage2() {
        String msg =  userDaoService.readMessage(LocaleContextHolder.getLocale());
        return new ResponseEntity<String>(msg,HttpStatus.OK);
    }
}
