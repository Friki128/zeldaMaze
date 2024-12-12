package net.esliceu.maze.Model;

public class game {
    int id;
    int user;
    int map;
    Integer currentRoom;
    int coinAmount;
    String time;
    boolean current;

    public game(int id, int user, int map, Integer currentRoom, int coinAmount, String time, boolean current) {
        this.id = id;
        this.user = user;
        this.map = map;
        this.currentRoom = currentRoom;
        this.coinAmount = coinAmount;
        this.time = time;
        this.current = current;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getMap() {
        return map;
    }

    public void setMap(int map) {
        this.map = map;
    }

    public Integer getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Integer currentRoom) {
        this.currentRoom = currentRoom;
    }

    public int getCoinAmount() {
        return coinAmount;
    }

    public void setCoinAmount(int coinAmount) {
        this.coinAmount = coinAmount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }
}
