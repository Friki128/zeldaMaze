package net.esliceu.maze.Dao;

import net.esliceu.maze.Model.map;

public interface mapDAO {
    void addMap(map map);
    void deleteMap(map map);
    void updateMap(map map);
    map getMap(int id);
}
