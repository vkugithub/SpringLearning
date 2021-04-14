package com.controller;

import com.domain.User;
import com.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.QueryParam;
import java.net.URI;

@RestController
@Validated
public class ValidationAtController {

    @Autowired
    UserDaoService userDaoService;

    @GetMapping("validation/users/{id}")
    public ResponseEntity<Object> getUser(@PathVariable @NotNull @Min(1)  int id) {
        User savedUer = userDaoService.findOne(id);

        return ResponseEntity.ok(savedUer); // with this type location will be header as well.
    }

    @GetMapping("validation/users")
    public ResponseEntity<Object> getUsers(@RequestParam @Min(1)  int id, @QueryParam("name") @NotNull @Size(min = 2)  String name) {
        User savedUer = userDaoService.findOne(id);

        return ResponseEntity.ok(savedUer); // with this type location will be header as well.
    }
}
