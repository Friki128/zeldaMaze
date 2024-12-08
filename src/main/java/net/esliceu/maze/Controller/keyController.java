package net.esliceu.maze.Controller;

import jakarta.servlet.http.HttpSession;
import net.esliceu.maze.Exceptions.*;
import net.esliceu.maze.Model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import net.esliceu.maze.Service.gameHandlerService;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class keyController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    gameHandlerService gameHandlerService;
    @GetMapping("/key")
    public String getKey() throws IOException{
        user user = (user) httpSession.getAttribute("user");
        try {
            gameHandlerService.collectKey(user);
            return "redirect:/maze";
        } catch (KeyDoesNotExistException e) {
            errorController.setMessage("Key does not exist");
            return "redirect:/error";
        } catch (GameDoesNotExistException e) {
            errorController.setMessage("Game does not exist");
            return "redirect:/error";
        } catch (RoomNotInMapException e) {
            errorController.setMessage("Room not in map");
            return "redirect:/error";
        } catch (NoKeyToCollectException e) {
            errorController.setMessage("No key to collect");
            return "redirect:/error";
        } catch (NotEnoughtFundsException e) {
            errorController.setMessage("Not enough funds");
            return "redirect:/error";
        } catch (RoomDoesNotExistException e) {
            errorController.setMessage("Room does not exist");
            return "redirect:/error";
        } catch (RoomConnectionDoesNotExist e) {
            errorController.setMessage("Room connection does not exist");
            return "redirect:/error";
        }
    }
}
