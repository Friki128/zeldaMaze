package net.esliceu.maze.Model;

public class keyItem {
    int id;
    String name;
    int cost;
    int position;

    public keyItem(int id, String name, int cost, int position) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.position = position;
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
