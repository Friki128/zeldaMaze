package net.esliceu.maze.Service;

import net.esliceu.maze.Dao.keyItemDAO;
import net.esliceu.maze.Dao.mapDAO;
import net.esliceu.maze.Dao.roomDAO;
import net.esliceu.maze.Dao.roomMapDAO;
import net.esliceu.maze.Exceptions.*;
import net.esliceu.maze.Model.keyItem;
import net.esliceu.maze.Model.map;
import net.esliceu.maze.Model.room;
import net.esliceu.maze.Model.roomMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class mazeComponentsService {
    @Autowired
    keyItemDAO keyItemDAO;
    @Autowired
    mapDAO mapDAO;
    @Autowired
    roomDAO roomDAO;
    @Autowired
    roomMapDAO roomMapDAO;
    public void createKey(String name, int cost){
        keyItem keyItem = new keyItem(0, name, cost);
        keyItemDAO.addKeyItem(keyItem);
    }
    public void deleteKey(int id) throws KeyDoesNotExistException {
        keyItem keyItem = getKeyItem(id);
        keyItemDAO.removeKeyItem(keyItem);
    }
    public void updateKeyName(int id, String name) throws KeyDoesNotExistException {
        keyItem keyItem = getKeyItem(id);
        keyItem.setName(name);
        keyItemDAO.updateKeyItem(keyItem);
    }
    public void updateKeyCost(int id, int cost) throws KeyDoesNotExistException {
        keyItem keyItem = getKeyItem(id);
        keyItem.setCost(cost);
        keyItemDAO.updateKeyItem(keyItem);
    }

    public void updateRoom(int id, String name, String up, String right, String down, String left, int coin, int key, int keyId) throws RoomDoesNotExistException {
        room room = getRoom(id);
        room.setUpDirection(up);
        room.setKeyPosition(key);
        room.setLeftDirection(left);
        room.setDownDirection(down);
        room.setRightDirection(right);
        room.setName(name);
        room.setCoinPosition(coin);
        room.setKeyId(keyId);
        roomDAO.updateRoom(room);
    }

    public void updateKey(int id, String name, int cost) throws KeyDoesNotExistException {
        keyItem item = getKeyItem(id);
        item.setCost(cost);
        item.setName(name);
        keyItemDAO.updateKeyItem(item);
    }

    public keyItem getKeyItem(int id) throws KeyDoesNotExistException {
        keyItem keyItem = keyItemDAO.findKeyItem(id);
        if(keyItem == null) throw new KeyDoesNotExistException();
        return keyItem;
    }
    public List<keyItem> getAllKeyItems(){
        return keyItemDAO.findKeyItems();
    }
    public void createRoom(int keyId, String name, int keyPosition, int coinPosition, String upDirection, String downDirection, String rightDirection, String leftDirection){
        room room = new room(0, keyId, name, keyPosition, coinPosition,upDirection, downDirection, leftDirection, rightDirection);
        roomDAO.addRoom(room);
    }
    public void deleteRoom(int id) throws RoomDoesNotExistException {
        room room = getRoom(id);
        roomDAO.deleteRoom(room);
    }
    public void addKeyToRoom(int id, int keyId, int position) throws KeyDoesNotExistException, RoomDoesNotExistException {
        room room = getRoom(id);
        keyItem keyItem = getKeyItem(keyId);
        room.setKeyId(keyItem.getId());
        room.setKeyPosition(position);
        roomDAO.updateRoom(room);
    }
    public void removeKeyToRoom(int id) throws RoomDoesNotExistException {
        room room = getRoom(id);
        room.setKeyPosition(-1);
        room.setKeyId(1);
        roomDAO.updateRoom(room);
    }
    public void updateKeyPosition(int id, int position) throws RoomDoesNotExistException {
        room room = getRoom(id);
        room.setKeyPosition(position);
        roomDAO.updateRoom(room);
    }
    public room getRoom(int id) throws RoomDoesNotExistException {
        room room = roomDAO.findRoom(id);
        if(room == null) throw new RoomDoesNotExistException();
        return room;
    }
    public List<room> getAllRooms(){
        return roomDAO.findRooms();
    }
    public void setCoinInRoom(int id, int position) throws RoomDoesNotExistException {
        room room = getRoom(id);
        room.setCoinPosition(position);
        roomDAO.updateRoom(room);
    }
    public void setRoomDirection(int id, String direction, String value) throws RoomDoesNotExistException, InvalidDirectionException {
        room room = getRoom(id);
        switch (direction){
            case "right":
                room.setRightDirection(value);
                break;
            case "left":
                room.setLeftDirection(value);
                break;
            case "down":
                room.setDownDirection(value);
                break;
            case "up":
                room.setUpDirection(value);
                break;
            default:
                throw new InvalidDirectionException();
        }
        roomDAO.updateRoom(room);
    }
    public void createMap(String name){
        map map = new map(0, name);
        mapDAO.addMap(map);
    }
    public void deleteMap(int id) throws MapDoesNotExistDirection {
        map map = getMap(id);
        mapDAO.deleteMap(map);
    }
    public void updateMapName(int id, String name) throws MapDoesNotExistDirection {
        map map = getMap(id);
        map.setName(name);
        mapDAO.updateMap(map);
    }
    public void updateStartRoom(int id, int startRoomId) throws MapDoesNotExistDirection {
        map map = getMap(id);
        map.setStartRoom(startRoomId);
        mapDAO.updateMap(map);
    }
    public void updateRoomMap(int id, int up, int down, int left, int right, String name) throws RoomConnectionDoesNotExist {
        roomMap roomMap = getRoomMap(id);
        roomMap.setUpDirection(up != -1 ?up :id);
        roomMap.setDownDirection(down != -1 ?up :id);
        roomMap.setLeftDirection(left != -1 ?up :id);
        roomMap.setRightDirection(right != -1 ?up :id);
        roomMap.setName(name);
        roomMapDAO.updateRoomMap(roomMap);
    }
    public map getMap(int id) throws MapDoesNotExistDirection {
        map map = mapDAO.findMap(id);
        if(map == null) throw new MapDoesNotExistDirection();
        return map;
    }
    public List<map> getAllMaps(){
        return mapDAO.findMaps();
    }
    public void addRoomToMap(int room, int map, String name){
        roomMap roomMap = new roomMap(0, room, map, name);
        roomMapDAO.addRoomMap(roomMap);
    }
    public void removeRoomToMap(int id) throws RoomConnectionDoesNotExist {
        roomMap roomMap = getRoomMap(id);
        roomMapDAO.deleteRoomMap(roomMap);
    }
    public void setRoomConnection(int id, String direction, int targetId) throws RoomConnectionDoesNotExist, InvalidDirectionException {
        roomMap roomMap = getRoomMap(id);
        switch (direction){
            case "right":
                roomMap.setRightDirection(targetId);
                break;
            case "left":
                roomMap.setLeftDirection(targetId);
                break;
            case "down":
                roomMap.setDownDirection(targetId);
                break;
            case "up":
                roomMap.setUpDirection(targetId);
                break;
            default:
                throw new InvalidDirectionException();
        }
        roomMapDAO.updateRoomMap(roomMap);
    }
    public roomMap getRoomMap(int id) throws RoomConnectionDoesNotExist {
        roomMap roomMap = roomMapDAO.findRoomMap(id);
        if(roomMap == null) throw new RoomConnectionDoesNotExist();
        return roomMap;
    }

    public List<roomMap> getRoomMapByMap(int mapId) {
        return roomMapDAO.findRoomMapByMap(mapId);
    }
}
