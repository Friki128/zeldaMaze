package net.esliceu.maze.Controller;

import jakarta.servlet.http.HttpSession;
import net.esliceu.maze.Exceptions.GameDoesNotExistException;
import net.esliceu.maze.Exceptions.NoCoinToCollectException;
import net.esliceu.maze.Exceptions.RoomNotInMapException;
import net.esliceu.maze.Model.user;
import net.esliceu.maze.Service.gameHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class coinController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    gameHandlerService gameHandlerService;
    @GetMapping("/coin")
    public String getCoin() throws IOException {
        user user = (user) httpSession.getAttribute("user");
        try {
            gameHandlerService.collectCoin(user);
            return "redirect:/maze";
        } catch (GameDoesNotExistException e) {
            errorController.setMessage("Game does not exist");
            return "redirect:/error";
        } catch (RoomNotInMapException e) {
            errorController.setMessage("Room not in map");
            return "redirect:/error";
        } catch (NoCoinToCollectException e) {
            errorController.setMessage("No coin to collect");
            return "redirect:/error";
        }
    }
}
