package net.esliceu.maze.Model;

public class map {
    int id;
    String name;
    int startRoom;

    public map(int id, String name, int startRoom) {
        this.id = id;
        this.name = name;
        this.startRoom = startRoom;
    }

    public map(int id, String name){
        this.name = name;
        this.id = id;
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

    public int getStartRoom() {
        return startRoom;
    }

    public void setStartRoom(int startRoom) {
        this.startRoom = startRoom;
    }
}
