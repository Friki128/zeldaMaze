package net.esliceu.maze.Controller;

import jakarta.servlet.http.HttpSession;
import net.esliceu.maze.Exceptions.*;
import net.esliceu.maze.Model.user;
import net.esliceu.maze.Service.gameHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class startController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    gameHandlerService gameHandlerService;
    @PostMapping("/start")
    public String postStart(Model model, @RequestParam int mapId) throws IOException{
        user user = (user) httpSession.getAttribute("user");
        try {
            gameHandlerService.startGame(user, mapId);
        } catch (MapDoesNotExistDirection e) {
            errorController.setMessage("Map does not exist");
            return "redirect:/error";
        } catch (GameAlreadyInMotionException e) {
            errorController.setMessage("Game already in motion");
            return "redirect:/error";
        } catch (RoomDoesNotExistException e) {
            errorController.setMessage("Room does not exist");
            return "redirect:/error";
        } catch (RoomConnectionDoesNotExist e) {
            errorController.setMessage("Room connection does not exist");
            return "redirect:/error";
        } catch (RoomNotInMapException e) {
            errorController.setMessage("Room not in map");
            return "redirect:/error";
        } catch (GameDoesNotExistException e) {
            errorController.setMessage("Game does not exist");
            return "redirect:/error";
        }
        return "redirect:/maze";
    }
}
