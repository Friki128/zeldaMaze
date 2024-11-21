package net.esliceu.maze.Service;

import net.esliceu.maze.Dao.gameRoomDAO;
import net.esliceu.maze.Dao.gameDAO;
import net.esliceu.maze.Dao.keyGameDAO;
import net.esliceu.maze.Exceptions.*;
import net.esliceu.maze.Model.*;
import net.esliceu.maze.Utils.timeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class gameHandlerService {
    @Autowired
    gameDAO gameDAO;
    @Autowired
    gameRoomDAO gameRoomDAO;
    @Autowired
    keyGameDAO keyGameDAO;
    @Autowired
    mazeComponentsService mazeComponentsService;
    public void startGame(user user, int mapId) throws MapDoesNotExistDirection, GameAlreadyInMotionException {
        map map = mazeComponentsService.getMap(mapId);
        game running = gameDAO.findCurrentGame(user.getId());
        if(running != null) throw new GameAlreadyInMotionException();
        game game = new game(0,user.getId(),mapId, map.getStartRoom(), 0, timeUtil.getTime(),true);
        gameDAO.addGame(game);
    }
    public void endGame(user user) throws GameDoesNotExistException {
        game game = getCurrentGame(user.getId());
        game.setPlaying(false);
        game.setTime(timeUtil.calcTimeDistance(game.getTime(), timeUtil.getTime()));
        gameDAO.updateGame(game);
    }
    public game getCurrentGame(int userId) throws GameDoesNotExistException {
        game game = gameDAO.findCurrentGame(userId);
        if(game == null) throw new GameDoesNotExistException();
        return game;
    }
    public List<game> getFinishedGames(){
        return gameDAO.findFinishedGames();
    }
    public void collectKey(user user) throws KeyDoesNotExistException, GameDoesNotExistException, RoomNotInMapException, NoKeyToCollectException, NotEnoughtFundsException, RoomDoesNotExistException, RoomConnectionDoesNotExist {
        game game = getCurrentGame(user.getId());
        gameRoom gameRoom = getGameRoomByGameAndRoom(game.getId(), game.getCurrentRoom());
        if(!gameRoom.isKeyStatus()) throw new NoKeyToCollectException();
        room room = mazeComponentsService.getRoom(mazeComponentsService.getRoomMap(gameRoom.getRoomMap()).getId());
        keyItem keyItem = mazeComponentsService.getKeyItem(room.getKeyId());
        if(game.getCoinAmount() < keyItem.getCost()) throw new NotEnoughtFundsException();
        removeKeyFromGameRoom(gameRoom.getId());
        keyGame keyGame = new keyGame(0, game.getId(), keyItem.getName());
        keyGameDAO.addKeyGame(keyGame);
    }
    public void useKey(user user, String keyName) throws GameDoesNotExistException, KeyNotInListException {
        game game = getCurrentGame(user.getId());
        keyGame keyGame = getKeyGameByName(game.getId(), keyName);
        keyGameDAO.removeKeyGame(keyGame);
    }
    public void collectCoin(user user) throws GameDoesNotExistException, RoomNotInMapException, NoCoinToCollectException {
        game game = getCurrentGame(user.getId());
        gameRoom gameRoom = getGameRoomByGameAndRoom(game.getId(), user.getId());
        if(!gameRoom.isCoinStatus()) throw new NoCoinToCollectException();
        removeCoinFromGameRoom(gameRoom.getId());
        game.setCoinAmount(game.getCoinAmount()+1);
        gameDAO.updateGame(game);
    }
    public List<keyGame> getKeyGamesByGame(int gameId){
        return keyGameDAO.findKeyGamesByGame(gameId);
    }
    public keyGame getKeyGameByName(int gameId, String name) throws KeyNotInListException {
        List<keyGame> keyGames = getKeyGamesByGame(gameId);
        for(keyGame keyGame : keyGames){
            if(keyGame.getName().equals(name)) return keyGame;
        }
        throw new KeyNotInListException();
    }
    public gameRoom getGameRoom(int id) throws RoomNotInMapException {
        gameRoom gameRoom = gameRoomDAO.findGameRoom(id);
        if(gameRoom == null) throw new RoomNotInMapException();
        return gameRoom;
    }
    public gameRoom getGameRoomByGameAndRoom(int gameId, int roomId) throws RoomNotInMapException {
        gameRoom gameRoom = gameRoomDAO.findGameRoomByGameAndRoom(gameId, roomId);
        if(gameRoom == null) throw new RoomNotInMapException();
        return gameRoom;
    }
    public void removeCoinFromGameRoom(int id) throws RoomNotInMapException {
        gameRoom gameRoom = getGameRoom(id);
        gameRoom.setCoinStatus(false);
        gameRoomDAO.updateGameRoom(gameRoom);
    }
    public void removeKeyFromGameRoom(int id) throws RoomNotInMapException {
        gameRoom gameRoom = getGameRoom(id);
        gameRoom.setKeyStatus(false);
        gameRoomDAO.updateGameRoom(gameRoom);
    }
}
