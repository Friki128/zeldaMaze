package net.esliceu.maze.Service;

import jakarta.el.LambdaExpression;
import net.esliceu.maze.Dao.gameRoomDAO;
import net.esliceu.maze.Dao.gameDAO;
import net.esliceu.maze.Dao.keyGameDAO;
import net.esliceu.maze.Exceptions.*;
import net.esliceu.maze.Model.*;
import net.esliceu.maze.Utils.playerGameInfo;
import net.esliceu.maze.Utils.timeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class gameHandlerService {
    @Autowired
    gameDAO gameDAO;
    @Autowired
    gameRoomDAO gameRoomDAO;
    @Autowired
    keyGameDAO keyGameDAO;
    @Autowired
    mazeComponentsService mazeComponentsService;

    public playerGameInfo getGameInfo(user user) throws GameDoesNotExistException, RoomNotInMapException, RoomConnectionDoesNotExist, RoomDoesNotExistException {
        game game = getCurrentGame(user.getId());
        gameRoom gameRoom = getGameRoom(game.getCurrentRoom());
        roomMap roomMap = mazeComponentsService.getRoomMap(gameRoom.getRoomMap());
        room room = mazeComponentsService.getRoom(roomMap.getRoom());
        return new playerGameInfo(game.getCoinAmount(), getKeyGamesByGame(game.getId()), gameRoom.getUpDirection(), gameRoom.getDownDirection(), gameRoom.getLeftDirection(), gameRoom.getRightDirection(), room.getKeyPosition(), room.getCoinPosition(), gameRoom.isKeyStatus(), gameRoom.isCoinStatus());
    }
    public void startGame(user user, int mapId) throws MapDoesNotExistDirection, GameAlreadyInMotionException, RoomDoesNotExistException, RoomConnectionDoesNotExist, RoomNotInMapException {
        map map = mazeComponentsService.getMap(mapId);
        game running = gameDAO.findCurrentGame(user.getId());
        if(running != null) throw new GameAlreadyInMotionException();
        game game = new game(0,user.getId(),mapId, 1, 0, timeUtil.getTime(),true);
        gameDAO.addGame(game);
        createGameRooms(user, mapId);
    }
    public void createGameRooms(user user, int mapId) throws RoomDoesNotExistException, MapDoesNotExistDirection, RoomConnectionDoesNotExist, RoomNotInMapException {
        game game = gameDAO.findCurrentGame(user.getId());
        map map = mazeComponentsService.getMap(mapId);
        List<roomMap> roomMaps = mazeComponentsService.getRoomMapByMap(mapId);
        for(roomMap roomMap : roomMaps){
            room room = mazeComponentsService.getRoom(roomMap.getRoom());
            gameRoom gameRoom = new gameRoom(0, game.getId(), roomMap.getId(), room.getKeyPosition() != -1, room.getCoinPosition() != -1, room.getUpDirection(), room.getDownDirection(), room.getLeftDirection(), room.getRightDirection());
        }
        roomMap startRoomMap = mazeComponentsService.getRoomMap(map.getStartRoom());
        gameRoom startGameRoom = getGameRoomByGameAndRoom(game.getId(), startRoomMap.getId());
        game.setCurrentRoom(startGameRoom.getId());
    }
    public void endGame(user user) throws GameDoesNotExistException {
        game game = getCurrentGame(user.getId());
        List<gameRoom> gameRooms = getGameRoomsByGame(game.getId());
        for(gameRoom gameRoom : gameRooms){
            gameRoomDAO.deleteGameRoom(gameRoom);
        }
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
    public void removeKeyGameByNameAndGame(int gameId, String name) throws KeyNotInListException {
        keyGame keyGame = getKeyGameByName(gameId, name);
        keyGameDAO.removeKeyGame(keyGame);
    }
    public gameRoom getGameRoom(int id) throws RoomNotInMapException {
        gameRoom gameRoom = gameRoomDAO.findGameRoom(id);
        if(gameRoom == null) throw new RoomNotInMapException();
        return gameRoom;
    }
    public List<gameRoom> getGameRoomsByGame(int gameId){
        return gameRoomDAO.findGameRoomByGame(gameId);
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
    public void move(user user, String direction) throws GameDoesNotExistException, RoomNotInMapException, RoomConnectionDoesNotExist, RoomDoesNotExistException, InvalidDirectionException, ClosedDoorException, NoDoorException {
        game game = getCurrentGame(user.getId());
        gameRoom gameRoom = getGameRoom(game.getCurrentRoom());
        roomMap roomMap = mazeComponentsService.getRoomMap(gameRoom.getRoomMap());
        String directionStatus = "";
        int newRoom = switch (direction) {
            case "N" -> {
                directionStatus = gameRoom.getUpDirection();
                yield roomMap.getUpDirection();
            }
            case "S" -> {
                directionStatus = gameRoom.getDownDirection();
                yield roomMap.getDownDirection();
            }
            case "E" -> {
                directionStatus = gameRoom.getRightDirection();
                yield roomMap.getRightDirection();
            }
            case "W" -> {
                directionStatus = gameRoom.getLeftDirection();
                yield roomMap.getLeftDirection();
            }
            default -> throw new InvalidDirectionException();
        };
        switch (directionStatus){
            case "Exit":
                endGame(user);
                break;
            case "Open":
                roomMap mapLoader = mazeComponentsService.getRoomMap(newRoom);
                gameRoom newCurrent = getGameRoomByGameAndRoom(game.getId(), mapLoader.getId());
                game.setCurrentRoom(newCurrent.getId());
                gameDAO.updateGame(game);
                break;
            case "Wall":
                throw new NoDoorException();
            default:
                throw new ClosedDoorException();
        }
    }
    public void openDoor(user user, String direction) throws GameDoesNotExistException, RoomNotInMapException, RoomConnectionDoesNotExist, RoomDoesNotExistException, InvalidDirectionException, IncorrectKeyException {
        game game = getCurrentGame(user.getId());
        gameRoom gameRoom = getGameRoom(game.getCurrentRoom());
        String directionStatus = "";
            switch (direction) {
                case "N":
                    directionStatus = gameRoom.getUpDirection();
                    break;
                case "S":
                    directionStatus = gameRoom.getDownDirection();
                    break;
                case "E":
                    directionStatus = gameRoom.getRightDirection();
                    break;
                case "W":
                    directionStatus = gameRoom.getLeftDirection();
                    break;
                default:
                    throw new InvalidDirectionException();
        };
        try {
            removeKeyGameByNameAndGame(game.getId(), directionStatus);
            switch (direction){
                case "N":
                    gameRoom.setUpDirection("Open");
                    break;
                case "S":
                    gameRoom.setDownDirection("Open");
                    break;
                case "E":
                    gameRoom.setRightDirection("Open");
                    break;
                case "W":
                    gameRoom.setLeftDirection("Open");
            }
        } catch (KeyNotInListException e) {
            throw new IncorrectKeyException();
        }
    }
}
