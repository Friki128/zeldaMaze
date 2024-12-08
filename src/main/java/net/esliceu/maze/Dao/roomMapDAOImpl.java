package net.esliceu.maze.Dao;

import net.esliceu.maze.Model.game;
import net.esliceu.maze.Model.roomMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class roomMapDAOImpl implements roomMapDAO{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public void addRoomMap(roomMap roomMap) {
        jdbcTemplate.update("INSERT INTO room_map(room, map, up_direction, down_direction, right_direction, left_direction, name) VALUES(?,?,?,?,?,?,?);", roomMap.getRoom(), roomMap.getMap(), roomMap.getUpDirection(), roomMap.getDownDirection(), roomMap.getRightDirection(), roomMap.getLeftDirection(), roomMap.getName());
    }

    @Override
    public void deleteRoomMap(roomMap roomMap) {
        jdbcTemplate.update("DELETE FROM room_map WHERE id=?;", roomMap.getId());
    }

    @Override
    public void updateRoomMap(roomMap roomMap) {
        jdbcTemplate.update("UPDATE room_map SET room=?, map=?, up_direction=?, down_direction=?. right_direction=?, left_direction, name=? WHERE id=?;", roomMap.getRoom(), roomMap.getMap(), roomMap.getUpDirection(), roomMap.getDownDirection(), roomMap.getRightDirection(), roomMap.getLeftDirection(), roomMap.getId(), roomMap.getName());
    }

    @Override
    public roomMap findRoomMap(int id) {
        try{
            return jdbcTemplate.queryForObject("SELECT * FROM room_map WHERE id=?;", new DataClassRowMapper<>(roomMap.class), id);
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public List<roomMap> findRoomMapByMap(int mapId) {
        return jdbcTemplate.query("SELECT * FROM room_map WHERE map=?;", new DataClassRowMapper<>(roomMap.class), mapId);
    }
}
