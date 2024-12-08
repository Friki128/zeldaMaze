package net.esliceu.maze.Utils;

import net.esliceu.maze.Model.keyGame;

import java.util.List;

public class playerGameInfo {
    int coinAmount;
    List<keyGame> keyNames;
    String roomName;
    String upDirection;
    String downDirection;
    String leftDirection;
    String rightDirection;
    int keyPosition;
    int coinPosition;
    boolean keyStatus;
    boolean coinStatus;

    public playerGameInfo(int coinAmount, List<keyGame> keyNames, String roomName,  String upDirection, String downDirection, String leftDirection, String rightDirection, int keyPosition, int coinPosition, boolean keyStatus, boolean coinStatus) {
        this.coinAmount = coinAmount;
        this.keyNames = keyNames;
        this.roomName = roomName;
        this.upDirection = upDirection;
        this.downDirection = downDirection;
        this.leftDirection = leftDirection;
        this.rightDirection = rightDirection;
        this.keyPosition = keyPosition;
        this.coinPosition = coinPosition;
        this.keyStatus = keyStatus;
        this.coinStatus = coinStatus;
    }

    public int getCoinAmount() {
        return coinAmount;
    }

    public void setCoinAmount(int coinAmount) {
        this.coinAmount = coinAmount;
    }

    public List<keyGame> getKeyNames() {
        return keyNames;
    }

    public void setKeyNames(List<keyGame> keyNames) {
        this.keyNames = keyNames;
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

    public boolean isKeyStatus() {
        return keyStatus;
    }

    public void setKeyStatus(boolean keyStatus) {
        this.keyStatus = keyStatus;
    }

    public boolean isCoinStatus() {
        return coinStatus;
    }

    public void setCoinStatus(boolean coinStatus) {
        this.coinStatus = coinStatus;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
