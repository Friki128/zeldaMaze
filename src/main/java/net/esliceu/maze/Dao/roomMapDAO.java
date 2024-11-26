package net.esliceu.maze.Dao;

import net.esliceu.maze.Model.roomMap;

import java.util.List;

public interface roomMapDAO {
    void addRoomMap(roomMap roomMap);
    void deleteRoomMap(roomMap roomMap);
    void updateRoomMap(roomMap roomMap);
    roomMap findRoomMap(int id);
    List<roomMap> findRoomMapByMap(int mapId);
}
