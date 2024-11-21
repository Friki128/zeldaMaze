package net.esliceu.maze.Dao;

import net.esliceu.maze.Model.gameRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class gameRoomDAOImpl implements gameRoomDAO{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public void addGameRoom(gameRoom gameRoom) {
        jdbcTemplate.update("INSERT INTO game_room(game, room_map, key_status, coin_status) VALUES()", gameRoom.getGame(), gameRoom.getRoomMap(), gameRoom.isKeyStatus(), gameRoom.isCoinStatus());
    }

    @Override
    public void deleteGameRoom(gameRoom gameRoom) {
        jdbcTemplate.update("DELETE FROM game_room WHERE id=?;", gameRoom.getId());
    }

    @Override
    public void updateGameRoom(gameRoom gameRoom) {
        jdbcTemplate.update("UPDATE game_room SET game=?, room_map=?, key_status=?, coin_status=? WHERE id=?;", gameRoom.getGame(), gameRoom.getRoomMap(), gameRoom.isKeyStatus(), gameRoom.isCoinStatus(), gameRoom.getId());
    }

    @Override
    public gameRoom findGameRoom(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM game_room WHERE id=?;", new DataClassRowMapper<>(gameRoom.class), id);
    }

    @Override
    public gameRoom findGameRoomByGameAndRoom(int gameId, int roomId) {
        return jdbcTemplate.queryForObject("SELECT * FROM game_room WHERE game=? AND room_map=?;", new DataClassRowMapper<>(gameRoom.class), gameId, roomId);
    }
}
