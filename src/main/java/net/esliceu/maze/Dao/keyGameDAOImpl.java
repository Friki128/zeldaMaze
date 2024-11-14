package net.esliceu.maze.Dao;

import net.esliceu.maze.Model.keyGame;
import net.esliceu.maze.Model.keyItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class keyGameDAOImpl implements keyGameDAO{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public void addKeyGame(keyGame keyGame) {
        jdbcTemplate.update("INSERT INTO key_game(game, name) VALUES(?.?);", keyGame.getGame(), keyGame.getName());
    }

    @Override
    public void removeKeyGame(keyGame keyGame) {
        jdbcTemplate.update("DELETE FROM key_game WHERE id=?;", keyGame.getId());
    }

    @Override
    public List<keyGame> findKeyGamesByGame(int game) {
        return jdbcTemplate.query("SELECT * FROM key_game WHERE game=?;", new DataClassRowMapper<>(keyGame.class), game);
    }
}
