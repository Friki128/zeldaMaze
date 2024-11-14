package net.esliceu.maze.Model;

public class game {
    int id;
    int user;
    int map;
    int currentRoom;
    int coinAmount;
    String time;
    boolean playing;

    public game(int id, int user, int map, int currentRoom, int coinAmount, String time, boolean playing) {
        this.id = id;
        this.user = user;
        this.map = map;
        this.currentRoom = currentRoom;
        this.coinAmount = coinAmount;
        this.time = time;
        this.playing = playing;
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

    public int getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(int currentRoom) {
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

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }
}
