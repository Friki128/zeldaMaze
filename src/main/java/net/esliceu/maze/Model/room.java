package net.esliceu.maze.Model;

public class room {
    int id;
    int keyId;
    String name;
    int keyPosition;
    int coinPosition;
    String upDirection;
    String downDirection;
    String leftDirection;
    String rightDirection;

    public room(int id, int keyId, String name, int keyPosition, int coinPosition, String upDirection, String downDirection, String leftDirection, String rightDirection) {
        this.id = id;
        this.keyId = keyId;
        this.keyPosition = keyPosition;
        this.coinPosition = coinPosition;
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

    public int getKeyId() {
        return keyId;
    }

    public void setKeyId(int keyId) {
        this.keyId = keyId;
    }

    public int getKeyPosition() {
        return keyPosition;
    }

    public void setKeyPosition(int keyPosition) {
        this.keyPosition = keyPosition;
    }

    public int getCoinPosition() {
        return coinPosition;
    }

    public void setCoinPosition(int coinPosition) {
        this.coinPosition = coinPosition;
    }

    public String getUpDirection() {
        return upDirection;
    }

    public void setUpDirection(String upDirection) {
        this.upDirection = upDirection;
    }

    public String getDownDirection() {
        return downDirection;
    }

    public void setDownDirection(String downDirection) {
        this.downDirection = downDirection;
    }

    public String getLeftDirection() {
        return leftDirection;
    }

    public void setLeftDirection(String leftDirection) {
        this.leftDirection = leftDirection;
    }

    public String getRightDirection() {
        return rightDirection;
    }

    public void setRightDirection(String rightDirection) {
        this.rightDirection = rightDirection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
