package net.esliceu.maze.Dao;

import net.esliceu.maze.Model.keyItem;

import java.util.List;

public interface keyItemDAO {
    void addKeyItem(keyItem keyItem);
    void removeKeyItem(keyItem keyItem);
    void updateKeyItem(keyItem keyItem);
    keyItem findKeyItem(int id);
    List<keyItem> findKeyItems();
}
