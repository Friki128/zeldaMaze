package net.esliceu.maze.Controller;

import jakarta.servlet.http.HttpSession;
import net.esliceu.maze.Exceptions.*;
import net.esliceu.maze.Model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import net.esliceu.maze.Service.gameHandlerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class openController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    gameHandlerService gameHandlerService;
    @GetMapping("/open")
    public String getOpen(@RequestParam String dir) throws IOException{
        user user = (user) httpSession.getAttribute("user");
        try {
            gameHandlerService.openDoor(user, dir);
            return "redirect:/maze";
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
        } catch (InvalidDirectionException e) {
            errorController.setMessage("Invalid Direction");
            return "redirect:/error";
        } catch (IncorrectKeyException e) {
            errorController.setMessage("Incorrect key");
            return "redirect:/error";
        }
    }
}
