package net.esliceu.maze.Dao;

import net.esliceu.maze.Model.map;

import java.util.List;

public interface mapDAO {
    void addMap(map map);
    void deleteMap(map map);
    void updateMap(map map);
    map findMap(int id);
    List<map> findMaps();
}
