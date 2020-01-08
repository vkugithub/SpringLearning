package com.service.impl;

import com.domain.User;
import com.exception.UserNotFoundException;
import com.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDaoServiceImpl implements UserDaoService {

    private static List<User> users = new ArrayList<>();

    private int usersCount = 3;

    static {
        users.add(new User(1, "adam", new Date()));
        users.add(new User(2, "jack", new Date()));
        users.add(new User(3, "paul", new Date()));
    }
    @Autowired
    private MessageSource messageSource;

    public List<User> findAll() {
        return users;
    }

    public User saveUser(User user) {
        if(user.getId() == null) {
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        for(User user : users) {
            if(user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }

    @Override
    public User updateUser(User user) {
        if(users.removeIf(u->u.getId()==user.getId())){
            users.add(user);
        }else{
            throw new UserNotFoundException("id -"+user.getId());
        }

        return user;
    }

    @Override
    public User patchUser(User user) {
        User user1=users.stream().filter(u->u.getId()==user.getId()).findFirst().get();
        user1.setName(user.getName());
        user1.setBirthDate(user.getBirthDate());
        return user1;
    }

    @Override
    public User searchUser(int id, String name) {
        User user1=users.stream().filter(u->u.getId()==id || name.equalsIgnoreCase(u.getName())).findAny().orElse(null);
        return user1;

    }

    @Override
    public String readMessage(Locale locale) {
        return messageSource.getMessage("good.morning.message",null,locale);
    }
}
