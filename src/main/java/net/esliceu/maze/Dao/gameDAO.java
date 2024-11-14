package net.esliceu.maze.Dao;

import net.esliceu.maze.Model.game;

import java.util.List;

public interface gameDAO {
    void addGame(game game);
    void restartGame(game game);
    List<game> findFinishedGames();
    game findCurrentGame(int userId);
    void updateCoinAmount(game game);
}
