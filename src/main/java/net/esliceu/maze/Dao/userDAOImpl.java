package net.esliceu.maze.Dao;

import net.esliceu.maze.Model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

@Repository
public class userDAOImpl implements userDAO{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public void addUser(user user) {
        jdbcTemplate.update("INSERT INTO user(name, password) VALUES(?, ?);", user.getName(), user.getPassword());
    }

    @Override
    public void deleteUser(user user) {
        jdbcTemplate.update("DELETE FROM user WHERE id = ?;", user.getId());
    }

    @Override
    public user findUserByNameAndPassword(String name, String password) {
        return jdbcTemplate.queryForObject("SELECT * FROM user WHERE user.name=? AND password=?;",  new DataClassRowMapper<>(user.class), name, password);
    }

    @Override
    public user findUserByName(String name) {
        return jdbcTemplate.queryForObject("SELECT * FROM user WHERE user.name=?;",  new DataClassRowMapper<>(user.class),name);
    }
}
