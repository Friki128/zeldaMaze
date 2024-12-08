package net.esliceu.maze.Controller;

import jakarta.servlet.http.HttpSession;
import net.esliceu.maze.Exceptions.*;
import net.esliceu.maze.Model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import net.esliceu.maze.Service.gameHandlerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class doorController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    gameHandlerService gameHandlerService;
    @GetMapping("/open")
    public String getOpen(@RequestParam String dir){
        user user = (user) httpSession.getAttribute("user");
        try {
            gameHandlerService.openDoor(user, dir);
        } catch (GameDoesNotExistException e) {
            errorController.setMessage("Game does not exist");
            return "redirect:/error";
        } catch (RoomNotInMapException e) {
            errorController.setMessage("Room not in map");
            return "redirect:/error";
        } catch (RoomConnectionDoesNotExist e) {
            errorController.setMessage("Room Connection does not exist");
            return "redirect:/error";
        } catch (RoomDoesNotExistException e) {
            errorController.setMessage("Room does not exist");
            return "redirect:/error";
        } catch (InvalidDirectionException e) {
            errorController.setMessage("Invalid Direction");
            return "redirect:/error";
        } catch (IncorrectKeyException e) {
            errorController.setMessage("Incorrect Key");
            return "redirect:/error";
        }
        return "redirect:/maze";
    }

}
