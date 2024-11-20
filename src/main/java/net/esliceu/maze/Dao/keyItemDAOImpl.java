package net.esliceu.maze.Dao;

import net.esliceu.maze.Model.keyItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class keyItemDAOImpl implements keyItemDAO{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public void addKeyItem(keyItem keyItem) {
        jdbcTemplate.update("INSERT INTO key_item(name, cost) VALUES(?, ?);", keyItem.getName(), keyItem.getCost());
    }

    @Override
    public void removeKeyItem(keyItem keyItem) {
        jdbcTemplate.update("DELETE FROM key_item WHERE id=?;", keyItem.getId());
    }

    @Override
    public void updateKeyItem(keyItem keyItem) {
        jdbcTemplate.update("UPDATE key_item SET name=?, cost=? WHERE id=?;", keyItem.getName(), keyItem.getCost(), keyItem.getId());
    }

    @Override
    public keyItem findKeyItem(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM key_item WHERE id=?;", new DataClassRowMapper<>(keyItem.class), id);
    }

    @Override
    public List<keyItem> findKeyItems() {
        return jdbcTemplate.query("SELECT * FROM key_item;", new DataClassRowMapper<>(keyItem.class));
    }
}
