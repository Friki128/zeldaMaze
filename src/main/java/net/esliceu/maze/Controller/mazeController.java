package net.esliceu.maze.Controller;

import jakarta.servlet.http.HttpSession;
import net.esliceu.maze.Exceptions.GameDoesNotExistException;
import net.esliceu.maze.Exceptions.RoomConnectionDoesNotExist;
import net.esliceu.maze.Exceptions.RoomDoesNotExistException;
import net.esliceu.maze.Exceptions.RoomNotInMapException;
import net.esliceu.maze.Model.user;
import net.esliceu.maze.Service.gameHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class mazeController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    gameHandlerService gameHandlerService;
    @GetMapping("/maze")
    public String getMaze(Model model) throws IOException {
        user user = (user) httpSession.getAttribute("user");
        try {
            model.addAttribute("status", gameHandlerService.getGameInfo(user));
            return "maze";
        } catch (GameDoesNotExistException e) {
            errorController.setMessage("Game does not exist");
            return "redirect:/error";
        } catch (RoomNotInMapException e) {
            errorController.setMessage("Room in map does not exist");
            return "redirect:/error";
        } catch (RoomConnectionDoesNotExist e) {
            errorController.setMessage("Room connection does not exist");
            return "redirect:/error";
        } catch (RoomDoesNotExistException e) {
            errorController.setMessage("Room does not exist");
            return "redirect:/error";
        }

    }
}
