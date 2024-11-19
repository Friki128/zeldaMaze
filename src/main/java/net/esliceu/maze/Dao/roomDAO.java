package net.esliceu.maze.Dao;

import net.esliceu.maze.Model.room;

import java.util.List;

public interface roomDAO {
    void addRoom(room room);
    void deleteRoom(room room);
    void updateRoom(room room);
    room findRoom(int id);
    List<room> findRooms();
}
