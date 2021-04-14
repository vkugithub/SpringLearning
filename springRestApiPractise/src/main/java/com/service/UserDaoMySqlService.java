package com.service;

import com.domain.User;

import java.util.List;
import java.util.Locale;

public interface UserDaoMySqlService {

    List<User> findAll();

    User saveUser(User user);

    User findOne(int id);

    User deleteById(int id);

    User updateUser(User user);

    User patchUser(User user);

    User searchUser(int id, String name);

    String readMessage(Locale locale);

    List<User> learnJdbcTemplateQueries(int id);
}
