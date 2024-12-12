package net.esliceu.maze.Service;

import net.esliceu.maze.Dao.gameRoomDAO;
import net.esliceu.maze.Dao.gameDAO;
import net.esliceu.maze.Dao.keyGameDAO;
import net.esliceu.maze.Exceptions.*;
import net.esliceu.maze.Model.*;
import net.esliceu.maze.Utils.playerGameInfo;
import net.esliceu.maze.Utils.timeUtil;
import net.esliceu.maze.Utils.score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    userService userService;

    public playerGameInfo getGameInfo(user user) throws GameDoesNotExistException, RoomNotInMapException, RoomConnectionDoesNotExist, RoomDoesNotExistException {
        game game = getCurrentGame(user.getId());
        gameRoom gameRoom = getGameRoom(game.getCurrentRoom());
        roomMap roomMap = mazeComponentsService.getRoomMap(gameRoom.getRoomMap());
        room room = mazeComponentsService.getRoom(roomMap.getRoom());
        return new playerGameInfo(game.getCoinAmount(), getKeyGamesByGame(game.getId()), roomMap.getName(), gameRoom.getUpDirection(), gameRoom.getDownDirection(), gameRoom.getLeftDirection(), gameRoom.getRightDirection(), room.getKeyPosition(), room.getCoinPosition(), gameRoom.isKeyStatus(), gameRoom.isCoinStatus());
    }
    public void startGame(user user, int mapId) throws MapDoesNotExistDirection, GameAlreadyInMotionException, RoomDoesNotExistException, RoomConnectionDoesNotExist, RoomNotInMapException, GameDoesNotExistException {
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
            gameRoomDAO.addGameRoom(gameRoom);
        }
        roomMap startRoomMap = mazeComponentsService.getRoomMap(map.getStartRoom());
        gameRoom startGameRoom = getGameRoomByGameAndRoom(game.getId(), startRoomMap.getId());
        game.setCurrentRoom(startGameRoom.getId());
        gameDAO.updateGame(game);
    }
    public String endGame(user user) throws GameDoesNotExistException {
        game game = getCurrentGame(user.getId());
        List<gameRoom> gameRooms = getGameRoomsByGame(game.getId());
        for(gameRoom gameRoom : gameRooms){
            gameRoomDAO.deleteGameRoom(gameRoom);
        }
        game.setCurrent(false);
        game.setTime(timeUtil.calcTimeDistance(game.getTime(), timeUtil.getTime()));
        gameDAO.updateGame(game);
        return game.getTime();
    }
    public void resetGame(user user) throws GameDoesNotExistException, MapDoesNotExistDirection, RoomDoesNotExistException, RoomConnectionDoesNotExist, RoomNotInMapException, GameAlreadyInMotionException {
        game game = getCurrentGame(user.getId());
        gameDAO.deleteGame(game);
        startGame(user, game.getMap());
    }
    public game getCurrentGame(int userId) throws GameDoesNotExistException {
        game game = gameDAO.findCurrentGame(userId);
        if(game == null) throw new GameDoesNotExistException();
        return game;
    }
    public List<score> getFinishedGames(){
        List<game> games = gameDAO.findFinishedGames();
        List<score> scores = new ArrayList<>();
        for(game game : games){
            user user = userService.getUser(game.getUser());
            scores.add(new score(user.getName(), game.getTime()));
        }
        return scores;
    }

    public List<score> getFinishedGamesByMap(int id){
        List<game> games = gameDAO.findFinishedGamesByMap(id);
        List<score> scores = new ArrayList<>();
        for(game game : games){
            user user = userService.getUser(game.getUser());
            scores.add(new score(user.getName(), game.getTime()));
        }
        return scores;
    }
    public void collectKey(user user) throws KeyDoesNotExistException, GameDoesNotExistException, RoomNotInMapException, NoKeyToCollectException, NotEnoughtFundsException, RoomDoesNotExistException, RoomConnectionDoesNotExist {
        game game = getCurrentGame(user.getId());
        gameRoom gameRoom = getGameRoom(game.getCurrentRoom());
        if(!gameRoom.isKeyStatus()) throw new NoKeyToCollectException();
        roomMap roomMap = mazeComponentsService.getRoomMap(gameRoom.getRoomMap());
        room room = mazeComponentsService.getRoom(roomMap.getRoom());
        keyItem keyItem = mazeComponentsService.getKeyItem(room.getKeyId());
        if(game.getCoinAmount() < keyItem.getCost()) throw new NotEnoughtFundsException();
        game.setCoinAmount(game.getCoinAmount() - keyItem.getCost());
        gameDAO.updateGame(game);
        removeKeyFromGameRoom(user);
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
        gameRoom gameRoom = getGameRoom(game.getCurrentRoom());
        if(!gameRoom.isCoinStatus()) throw new NoCoinToCollectException();
        removeCoinFromGameRoom(user);
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
    public List<gameRoom> getGameRoomsByGame(int gameId){
        return gameRoomDAO.findGameRoomByGame(gameId);
    }
    public gameRoom getGameRoomByGameAndRoom(int gameId, int roomId) throws RoomNotInMapException {
        gameRoom gameRoom = gameRoomDAO.findGameRoomByGameAndRoom(gameId, roomId);
        if(gameRoom == null) throw new RoomNotInMapException();
        return gameRoom;
    }
    public void removeCoinFromGameRoom(user user) throws RoomNotInMapException, GameDoesNotExistException {
        game game = getCurrentGame(user.getId());
        gameRoom gameRoom = getGameRoom(game.getCurrentRoom());
        gameRoom.setCoinStatus(false);
        gameRoomDAO.updateGameRoom(gameRoom);
    }
    public void removeKeyFromGameRoom(user user) throws RoomNotInMapException, GameDoesNotExistException {
        game game = getCurrentGame(user.getId());
        gameRoom gameRoom = getGameRoom(game.getCurrentRoom());
        gameRoom.setKeyStatus(false);
        gameRoomDAO.updateGameRoom(gameRoom);
    }
    public void move(user user, String direction) throws GameDoesNotExistException, RoomNotInMapException, RoomConnectionDoesNotExist, RoomDoesNotExistException, InvalidDirectionException, ClosedDoorException, NoDoorException {
        game game = getCurrentGame(user.getId());
        gameRoom gameRoom = getGameRoom(game.getCurrentRoom());
        roomMap roomMap = mazeComponentsService.getRoomMap(gameRoom.getRoomMap());
        String directionStatus;
        Integer newRoom = switch (direction) {
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
        String directionStatus = switch (direction) {
            case "N" -> gameRoom.getUpDirection();
            case "S" -> gameRoom.getDownDirection();
            case "E" -> gameRoom.getRightDirection();
            case "W" -> gameRoom.getLeftDirection();
            default -> throw new InvalidDirectionException();
        };
        try {
            useKey(user, directionStatus);
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
                    break;
            }
            gameRoomDAO.updateGameRoom(gameRoom);
        } catch (KeyNotInListException e) {
            throw new IncorrectKeyException();
        }
    }
}
