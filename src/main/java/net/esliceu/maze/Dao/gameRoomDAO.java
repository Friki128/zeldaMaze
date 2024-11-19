package net.esliceu.maze.Dao;

import net.esliceu.maze.Model.gameRoom;

public interface gameRoomDAO {
    void addGameRoom(gameRoom gameRoom);
    void deleteGameRoom(gameRoom gameRoom);
    void updateGameRoom(gameRoom gameRoom);
    gameRoom findGameRoomByGameAndRoom(int gameId, int roomId);
}
