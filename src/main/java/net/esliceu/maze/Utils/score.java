package net.esliceu.maze.Utils;

public class score {
    String user;
    String time;

    public score(String user, String time) {
        this.user = user;
        this.time = time;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
