package net.esliceu.maze.Dao;

import net.esliceu.maze.Model.roomMap;

public interface roomMapDAO {
    void addRoomMap(roomMap roomMap);
    void deleteRoomMap(roomMap roomMap);
    void updateRoomMap(roomMap roomMap);
    roomMap findRoomMap(int id);
}
