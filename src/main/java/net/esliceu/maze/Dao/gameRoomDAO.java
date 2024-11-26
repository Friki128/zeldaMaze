package net.esliceu.maze.Dao;

import net.esliceu.maze.Model.gameRoom;

import java.util.List;

public interface gameRoomDAO {
    void addGameRoom(gameRoom gameRoom);
    void deleteGameRoom(gameRoom gameRoom);
    void updateGameRoom(gameRoom gameRoom);
    gameRoom findGameRoom(int id);
    List<gameRoom> findGameRoomByGame(int id);
    gameRoom findGameRoomByGameAndRoom(int gameId, int roomId);
}
