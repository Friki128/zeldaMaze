package net.esliceu.maze.Model;

public class roomMap {
    int id;
    int room;
    int map;
    int upDirection;
    int downDirection;
    int leftDirection;
    int rightDirection;

    public roomMap(int id, int room, int map, int upDirection, int downDirection, int leftDirection, int rightDirection) {
        this.id = id;
        this.room = room;
        this.map = map;
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

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getMap() {
        return map;
    }

    public void setMap(int map) {
        this.map = map;
    }

    public int getUpDirection() {
        return upDirection;
    }

    public void setUpDirection(int upDirection) {
        this.upDirection = upDirection;
    }

    public int getDownDirection() {
        return downDirection;
    }

    public void setDownDirection(int downDirection) {
        this.downDirection = downDirection;
    }

    public int getLeftDirection() {
        return leftDirection;
    }

    public void setLeftDirection(int leftDirection) {
        this.leftDirection = leftDirection;
    }

    public int getRightDirection() {
        return rightDirection;
    }

    public void setRightDirection(int rightDirection) {
        this.rightDirection = rightDirection;
    }
}
