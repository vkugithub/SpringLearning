package com.service;


import com.domain.User;
import org.springframework.stereotype.Component;

import java.util.*;


public interface UserDaoService {

    List<User> findAll();

    User saveUser(User user);

    User findOne(int id);

    User deleteById(int id);

    User updateUser(User user);

    User patchUser(User user);

    User searchUser(int id, String name);

    String readMessage(Locale locale);
}
