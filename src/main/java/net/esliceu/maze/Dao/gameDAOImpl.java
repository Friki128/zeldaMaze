package net.esliceu.maze.Dao;

import net.esliceu.maze.Model.game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class gameDAOImpl implements gameDAO{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public void addGame(game game) {
        jdbcTemplate.update("INSERT INTO game(user, map, current_room, coin_amount, time, current) VALUES(?, ?, ?, ?, ?, ?);", game.getUser(), game.getMap(), game.getCurrentRoom(), game.getCoinAmount(), game.getTime(), game.getCurrentRoom());
    }

    @Override
    public void updateGame(game game) {
        jdbcTemplate.update("UPDATE game SET user=?, map=?, current_room=?, coin_amount=?, time=?, current=? WHERE id=?;", game.getUser(), game.getMap(), game.getCurrentRoom(), game.getCoinAmount(), game.getTime(), game.isPlaying(), game.getId());
    }

    @Override
    public List<game> findFinishedGames() {
        return jdbcTemplate.query("SELECT * FROM game WHERE current=?;", new DataClassRowMapper<>(game.class),false);
    }

    @Override
    public game findCurrentGame(int userId) {
        return jdbcTemplate.queryForObject("SELECT * FROM game WHERE user=? AND current=?;", new DataClassRowMapper<>(game.class), userId, true);
    }
}
