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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class coinController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    gameHandlerService gameHandlerService;
    @GetMapping("/coin")
    public String getCoin(RedirectAttributes redirectAttributes) throws IOException {
        user user = (user) httpSession.getAttribute("user");
        try {
            gameHandlerService.collectCoin(user);
            return "redirect:/maze";
        } catch (GameDoesNotExistException e) {
            redirectAttributes.addAttribute("error", "Game does not exist.");
        } catch (RoomNotInMapException e) {
            redirectAttributes.addAttribute("error", "Room doesn't exist in maze.");
        } catch (NoCoinToCollectException e) {
            redirectAttributes.addAttribute("error", "No coin to collect.");
        }
        return "redirect:/error";
    }
}
