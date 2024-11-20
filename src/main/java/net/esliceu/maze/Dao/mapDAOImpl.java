package net.esliceu.maze.Dao;

import net.esliceu.maze.Model.map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class mapDAOImpl implements mapDAO{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void addMap(map map) {
        jdbcTemplate.update("INSERT INTO map(name, start_room) VALUES(?,?);", map.getName(), map.getStartRoom());
    }

    @Override
    public void deleteMap(map map) {
        jdbcTemplate.update("DELETE FROM map WHERE id=?;", map.getId());
    }

    @Override
    public void updateMap(map map) {
        jdbcTemplate.update("UPDATE map SET name=?, start_room=? WHERE id=?;", map.getName(), map.getStartRoom(), map.getId());
    }

    @Override
    public map findMap(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM map WHERE id=?;", new DataClassRowMapper<>(map.class), id);
    }

    @Override
    public List<map> findMaps() {
        return jdbcTemplate.query("SELECT * FROM map;", new DataClassRowMapper<>(map.class));
    }
}
