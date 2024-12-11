package net.esliceu.maze.Model;

import org.springframework.data.relational.core.sql.In;

public class roomMap {
    int id;
    int room;
    int map;
    Integer upDirection;
    Integer downDirection;
    Integer leftDirection;
    Integer rightDirection;
    String name;

    public roomMap(int id, int room, int map, Integer upDirection, Integer downDirection, Integer leftDirection, Integer rightDirection, String name) {
        this.id = id;
        this.room = room;
        this.map = map;
        this.upDirection = upDirection;
        this.downDirection = downDirection;
        this.leftDirection = leftDirection;
        this.rightDirection = rightDirection;
        this.name = name;
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

    public Integer getUpDirection() {
        return upDirection;
    }

    public void setUpDirection(Integer upDirection) {
        this.upDirection = upDirection;
    }

    public Integer getDownDirection() {
        return downDirection;
    }

    public void setDownDirection(Integer downDirection) {
        this.downDirection = downDirection;
    }

    public Integer getLeftDirection() {
        return leftDirection;
    }

    public void setLeftDirection(Integer leftDirection) {
        this.leftDirection = leftDirection;
    }

    public Integer getRightDirection() {
        return rightDirection;
    }

    public void setRightDirection(Integer rightDirection) {
        this.rightDirection = rightDirection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
