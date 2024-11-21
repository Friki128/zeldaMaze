package net.esliceu.maze.Service;

import net.esliceu.maze.Dao.userDAO;
import net.esliceu.maze.Exceptions.IncorrectLoginException;
import net.esliceu.maze.Exceptions.UsernameInUseException;
import net.esliceu.maze.Model.user;
import net.esliceu.maze.Utils.hashCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class userService {
    @Autowired
    userDAO userDAO;
    void register(String name, String password) throws UsernameInUseException {
        if(!checkUserNameAvailability(name)) throw new UsernameInUseException();
        user user = new user(0, name, hashCreator.hash(password), false);
        userDAO.addUser(user);
    }
    void deleteUser(int id){
        user user = userDAO.findUser(id);
        userDAO.deleteUser(user);
    }
    boolean checkUserNameAvailability(String name){
        user user = userDAO.findUserByName(name);
        return user != null;
    }
    user login(String name, String password) throws IncorrectLoginException {
        user user = userDAO.findUserByNameAndPassword(name, hashCreator.hash(password));
        if(user == null) throw new IncorrectLoginException();
        return user;
    }
}
