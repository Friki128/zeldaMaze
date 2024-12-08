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
        jdbcTemplate.update("INSERT INTO user(name, password, admin) VALUES(?, ?, ?);", user.getName(), user.getPassword(), user.isAdmin());
    }

    @Override
    public void deleteUser(user user) {
        jdbcTemplate.update("DELETE FROM user WHERE id = ?;", user.getId());
    }

    @Override
    public user findUser(int id) {
        try{
            return jdbcTemplate.queryForObject("SELECT * FROM user WHERE id=?;",  new DataClassRowMapper<>(user.class), id);
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public user findUserByNameAndPassword(String name, String password) {
        try{
            return jdbcTemplate.queryForObject("SELECT * FROM user WHERE name=? AND password=?;",  new DataClassRowMapper<>(user.class), name, password);
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public user findUserByName(String name) {
        try{
            return jdbcTemplate.queryForObject("SELECT * FROM user WHERE user.name=?;",  new DataClassRowMapper<>(user.class),name);
        }catch (Exception e){
            return null;
        }

    }
}
