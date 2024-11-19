package net.esliceu.maze.Dao;

import net.esliceu.maze.Model.room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class roomDAOImpl implements roomDAO{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public void addRoom(room room) {
        jdbcTemplate.update("INSERT INTO room(key_id, coin_position, up_direction, down_direction, right_direction, left_direction) VALUES(?,?,?,?,?,?);", room.getKeyId(), room.getCoinPosition(), room.getUpDirection(), room.getDownDirection(), room.getRightDirection(), room.getLeftDirection());
    }

    @Override
    public void deleteRoom(room room) {
        jdbcTemplate.update("DELETE FROM room WHERE id=?;", room.getId());
    }

    @Override
    public void updateRoom(room room) {
        jdbcTemplate.update("UPDATE room SET key_id=?, coin_position=?, up_direction=?, down_direction=?, right_direction=?, left_direction=? WHERE id=?;", room.getKeyId(), room.getCoinPosition(), room.getUpDirection(), room.getDownDirection(), room.getRightDirection(), room.getLeftDirection(), room.getId());
    }

    @Override
    public room findRoom(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM room WHERE id=?;", new DataClassRowMapper<>(room.class), id);
    }

    @Override
    public List<room> findRooms() {
        return jdbcTemplate.query("SELECT * FROM room;", new DataClassRowMapper<>(room.class));
    }
}
