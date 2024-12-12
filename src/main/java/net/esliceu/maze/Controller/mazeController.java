package net.esliceu.maze.Controller;

import jakarta.servlet.http.HttpSession;
import net.esliceu.maze.Exceptions.GameDoesNotExistException;
import net.esliceu.maze.Exceptions.RoomConnectionDoesNotExist;
import net.esliceu.maze.Exceptions.RoomDoesNotExistException;
import net.esliceu.maze.Exceptions.RoomNotInMapException;
import net.esliceu.maze.Model.user;
import net.esliceu.maze.Service.gameHandlerService;
import net.esliceu.maze.Utils.playerGameInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class mazeController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    gameHandlerService gameHandlerService;
    @GetMapping("/maze")
    public String getMaze(Model model, RedirectAttributes redirectAttributes, @RequestParam(required = false) String error) throws IOException {
        user user = (user) httpSession.getAttribute("user");
        try {
            playerGameInfo gameInfo = gameHandlerService.getGameInfo(user);
            if(gameInfo.getRoomName().equals("Exit")){
                redirectAttributes.addAttribute("time", gameHandlerService.endGame(user));
                return "redirect:/end";
            }
            model.addAttribute("status", gameInfo);
            model.addAttribute("error", error);
            return "maze";
        } catch (GameDoesNotExistException e) {
            redirectAttributes.addAttribute("error", "Game does not exist.");
        } catch (RoomNotInMapException e) {
            redirectAttributes.addAttribute("error", "Room not in maze.");
        } catch (RoomConnectionDoesNotExist e) {
            redirectAttributes.addAttribute("error", "Room connection does not exist.");
        } catch (RoomDoesNotExistException e) {
            redirectAttributes.addAttribute("error", "Room does not exist.");
        }
        return "redirect:/errorDisplay";
    }
}
