package net.esliceu.maze.Model;

import org.springframework.beans.factory.annotation.Autowired;

public class map {
    int id;
    String name;
    Integer startRoom;

    @Autowired
    public map(int id, String name, Integer startRoom) {
        this.id = id;
        this.name = name;
        this.startRoom = startRoom;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStartRoom() {
        return startRoom;
    }

    public void setStartRoom(Integer startRoom) {
        this.startRoom = startRoom;
    }
}
