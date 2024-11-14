package net.esliceu.maze.Model;

public class gameRoom {
    int id;
    int game;
    int roomMap;
    boolean keyStatus;
    boolean coinStatus;

    public gameRoom(int id, int game, int roomMap, boolean keyStatus, boolean coinStatus) {
        this.id = id;
        this.game = game;
        this.roomMap = roomMap;
        this.keyStatus = keyStatus;
        this.coinStatus = coinStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public int getRoomMap() {
        return roomMap;
    }

    public void setRoomMap(int roomMap) {
        this.roomMap = roomMap;
    }

    public boolean isKeyStatus() {
        return keyStatus;
    }

    public void setKeyStatus(boolean keyStatus) {
        this.keyStatus = keyStatus;
    }

    public boolean isCoinStatus() {
        return coinStatus;
    }

    public void setCoinStatus(boolean coinStatus) {
        this.coinStatus = coinStatus;
    }
}
