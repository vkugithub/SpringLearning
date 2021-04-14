package com.service.impl;

import com.domain.User;
import com.exception.UserNotFoundException;
import com.service.UserDaoMySqlService;
import com.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

@Component
public class UserDaoMySqlServiceImpl implements UserDaoMySqlService {

        private static List<User> users = new ArrayList<>();

        private int usersCount = 3;

        static {
            users.add(new User(1, "adam", new Date()));
            users.add(new User(2, "jack", new Date()));
            users.add(new User(3, "paul", new Date()));
        }
        @Autowired
        private JdbcTemplate jdbcTemplate;

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
            Map map=jdbcTemplate.queryForMap("select id, name, birth_date from user where id="+id);
            User user=new User();
            user.setId(id);
            user.setName((String)map.get("name"));
            user.setBirthDate((Date)map.get("birth_date"));
            return user;
        }

    public List<User> queryForList(int id) {
        List<Map<String,Object>> list=jdbcTemplate.queryForList("select id, name, birth_date from user ");
        List<User> users=new ArrayList<>();
        for(Map map:list){
            User user=new User();
            user.setId(id);
            user.setName((String)map.get("name"));
            user.setBirthDate((Date)map.get("birth_date"));
            users.add(user);
        }

        return users;
    }

    public List<User> learnJdbcTemplateQueries(int id) {
        List<Map<String,Object>> list=jdbcTemplate.queryForList("select id, name, birth_date from user ");
        List<User> users=new ArrayList<>();
        for(Map map:list){
            User user=new User();
            user.setId(id);
            user.setName((String)map.get("name"));
            user.setBirthDate((Date)map.get("birth_date"));
            users.add(user);
        }
        String SQL = "select name from user where id = ?";
        String name = jdbcTemplate.queryForObject(SQL, new Object[]{2},
                String.class);
        System.out.println("name "+name);


        User user = jdbcTemplate.queryForObject("select * from user where id = ?",
                new Object[]{2},new UserMapper());

        System.out.println("user "+user);

        User user2 = jdbcTemplate.queryForObject("select * from user where id=?",
                new Object[]{2}, (rs,rowNum)->{
                    User obj=new User();
                    obj.setId(rs.getInt("id"));
                    obj.setName(rs.getString("name"));
                    obj.setBirthDate(rs.getDate("birth_date"));
                    return obj;
                });
        System.out.println("user2 "+user2);


        List<User> users2 = jdbcTemplate.query("select * from user",
                new UserMapper());

        System.out.println(" List<User> "+users2);

        String insertQuery = "insert into user (id, name, birth_date) values (?, ?, ?)";
//        jdbcTemplate.update( insertQuery, new Object[]{1000,"Vikrsnt", LocalDate.of(1965,10,05)} );

        String updateQuery = "update user set name=? where id=?";
        jdbcTemplate.update( updateQuery, new Object[]{"Vikrant Thakran",1000} );

        String deleteQuery = "delete from user where id = ?";
        jdbcTemplate.update( deleteQuery, new Object[]{1000} );

        String createQuery = "CREATE TABLE Student( " +
                "ID   INT NOT NULL AUTO_INCREMENT, " +
                "NAME VARCHAR(20) NOT NULL, " +
                "AGE  INT NOT NULL, " +
                "PRIMARY KEY (ID));";

        jdbcTemplate.execute( createQuery );

        return users;
    }
    class UserMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException
                {
                    User user=new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setBirthDate(rs.getDate("birth_date"));
                    return user;
                }
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
