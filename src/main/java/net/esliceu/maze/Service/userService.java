package net.esliceu.maze.Service;

import net.esliceu.maze.Dao.userDAO;
import net.esliceu.maze.Exceptions.EmptyNameException;
import net.esliceu.maze.Exceptions.IncorrectLoginException;
import net.esliceu.maze.Exceptions.PasswordTooShortException;
import net.esliceu.maze.Exceptions.UsernameInUseException;
import net.esliceu.maze.Model.user;
import net.esliceu.maze.Utils.hashCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userService {
    @Autowired
    userDAO userDAO;
    public void register(String name, String password) throws UsernameInUseException, PasswordTooShortException, EmptyNameException {
        name = name.replace(" ", "");
        if(name.isEmpty()) throw new EmptyNameException();
        if(checkUserNameAvailability(name)) throw new UsernameInUseException();
        if(password.length() < 5) throw new PasswordTooShortException();
        user user = new user(0, name, hashCreator.hash(password), false);
        userDAO.addUser(user);
    }
    public void deleteUser(int id){
        user user = userDAO.findUser(id);
        userDAO.deleteUser(user);
    }
    public boolean checkUserNameAvailability(String name){
        user user = userDAO.findUserByName(name);
        return user != null;
    }
    public user login(String name, String password) throws IncorrectLoginException {
        name = name.replace(" ", "");
        user user = userDAO.findUserByNameAndPassword(name, hashCreator.hash(password));
        if(user == null) throw new IncorrectLoginException();
        return user;
    }
    public user getUser(int id){
        return userDAO.findUser(id);
    }
}
