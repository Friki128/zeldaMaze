package net.esliceu.maze.Model;

public class gameRoom {
    int id;
    int game;
    int roomMap;
    boolean keyStatus;
    boolean coinStatus;
    String upDirection;
    String downDirection;
    String leftDirection;
    String rightDirection;

    public gameRoom(int id, int game, int roomMap, boolean keyStatus, boolean coinStatus, String upDirection, String downDirection, String leftDirection, String rightDirection) {
        this.id = id;
        this.game = game;
        this.roomMap = roomMap;
        this.keyStatus = keyStatus;
        this.coinStatus = coinStatus;
        this.upDirection = upDirection;
        this.downDirection = downDirection;
        this.leftDirection = leftDirection;
        this.rightDirection = rightDirection;
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

    public String getUpDirection() {
        return upDirection;
    }

    public void setUpDirection(String upDirection) {
        this.upDirection = upDirection;
    }

    public String getDownDirection() {
        return downDirection;
    }

    public void setDownDirection(String downDirection) {
        this.downDirection = downDirection;
    }

    public String getLeftDirection() {
        return leftDirection;
    }

    public void setLeftDirection(String leftDirection) {
        this.leftDirection = leftDirection;
    }

    public String getRightDirection() {
        return rightDirection;
    }

    public void setRightDirection(String rightDirection) {
        this.rightDirection = rightDirection;
    }

}
