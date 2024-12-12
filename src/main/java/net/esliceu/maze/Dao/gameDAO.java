package net.esliceu.maze.Dao;

import net.esliceu.maze.Model.game;

import java.util.List;

public interface gameDAO {
    void addGame(game game);
    void updateGame(game game);
    void deleteGame(game game);
    List<game> findFinishedGames();
    List<game> findFinishedGamesByMap(int id);
    game findCurrentGame(int userId);
}
