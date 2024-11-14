package net.esliceu.maze.Dao;

import net.esliceu.maze.Model.keyGame;

import java.util.List;

public interface keyGameDAO {
    void addKeyGame(keyGame keyGame);
    void removeKeyGame(keyGame keyGame);
    List<keyGame> findKeyGamesByGame(int game);
}
