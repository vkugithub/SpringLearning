package com.controller;

import com.domain.User;
import com.service.UserDaoMySqlService;
import com.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.QueryParam;
import java.sql.SQLException;
import java.util.List;

@RestController
public class UserMySqlController {

    @Autowired
    UserDaoMySqlService userDaoMySqlService;

    @GetMapping("mysql/users/{id}")
    public ResponseEntity<Object> getUser(@PathVariable @NotNull @Min(1)  int id) {
        User savedUer = userDaoMySqlService.findOne(id);

        return ResponseEntity.ok(savedUer); // with this type location will be header as well.
    }

    @GetMapping("mysql/users")
    public ResponseEntity<Object> getUsers(@RequestParam @Min(1)  int id, @QueryParam("name") @NotNull @Size(min = 2)  String name) {
        User savedUer = userDaoMySqlService.findOne(id);

        return ResponseEntity.ok(savedUer); // with this type location will be header as well.
    }

    @GetMapping("mysql/learnQueries")
    //By default, a transaction will be rolling back on RuntimeException and Error but not on checked exceptions (business exceptions)
    @Transactional(noRollbackFor={NullPointerException.class}, rollbackFor={SQLException.class}, timeout = 120)
    public ResponseEntity<List<User>> learnQueries(@RequestParam @Min(1)  int id, @QueryParam("name") @Size(min = 2)  String name) {
        List<User> users = userDaoMySqlService.learnJdbcTemplateQueries(id);

        return ResponseEntity.ok(users); // with this type location will be header as well.
    }
}
