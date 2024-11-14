package net.esliceu.maze.Model;

public class room {
    int id;
    int key_id;
    int coin_position;
    String up_direction;
    String down_direction;
    String left_direction;
    String right_direction;

    public room(int id, int key_id, int coin_position, String up_direction, String down_direction, String left_direction, String right_direction) {
        this.id = id;
        this.key_id = key_id;
        this.coin_position = coin_position;
        this.up_direction = up_direction;
        this.down_direction = down_direction;
        this.left_direction = left_direction;
        this.right_direction = right_direction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKey_id() {
        return key_id;
    }

    public void setKey_id(int key_id) {
        this.key_id = key_id;
    }

    public int getCoin_position() {
        return coin_position;
    }

    public void setCoin_position(int coin_position) {
        this.coin_position = coin_position;
    }

    public String getUp_direction() {
        return up_direction;
    }

    public void setUp_direction(String up_direction) {
        this.up_direction = up_direction;
    }

    public String getDown_direction() {
        return down_direction;
    }

    public void setDown_direction(String down_direction) {
        this.down_direction = down_direction;
    }

    public String getLeft_direction() {
        return left_direction;
    }

    public void setLeft_direction(String left_direction) {
        this.left_direction = left_direction;
    }

    public String getRight_direction() {
        return right_direction;
    }

    public void setRight_direction(String right_direction) {
        this.right_direction = right_direction;
    }
}
