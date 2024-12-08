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
public class moveController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    gameHandlerService gameHandlerService;
    @GetMapping("/move")
    public String getMove(@RequestParam String dir) throws IOException{
        user user = (user) httpSession.getAttribute("user");
        try {
            String time = gameHandlerService.move(user, dir);
            if(!time.isEmpty()){
                httpSession.setAttribute("time", time);
                return "redirect:/end";
            }
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
            errorController.setMessage("Invalid direction");
            return "redirect:/error";
        } catch (ClosedDoorException e) {
            errorController.setMessage("The door is closed");
            return "redirect:/error";
        } catch (NoDoorException e) {
            errorController.setMessage("There isn't a path in that direction");
            return "redirect:/error";
        }
    }
}
