package net.esliceu.maze.Dao;

import net.esliceu.maze.Model.user;

public interface userDAO {
    void addUser(user user);
    void deleteUser(user user);
    user findUserByNameAndPassword(String name, String password);
    user findUserByName(String name);
}
