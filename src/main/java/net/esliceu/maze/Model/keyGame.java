package net.esliceu.maze.Model;

public class keyGame {
    int id;
    int game;
    String name;

    public keyGame(int id, int game, String name) {
        this.id = id;
        this.game = game;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
