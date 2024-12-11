package net.esliceu.maze.Controller;

import jakarta.servlet.http.HttpSession;
import net.esliceu.maze.Exceptions.*;
import net.esliceu.maze.Model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import net.esliceu.maze.Service.gameHandlerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class keyController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    gameHandlerService gameHandlerService;
    @GetMapping("/key")
    public String getKey(RedirectAttributes redirectAttributes) throws IOException{
        user user = (user) httpSession.getAttribute("user");
        try {
            gameHandlerService.collectKey(user);
            return "redirect:/maze";
        } catch (KeyDoesNotExistException e) {
            redirectAttributes.addAttribute("error", "Key does not exist.");
        } catch (GameDoesNotExistException e) {
            redirectAttributes.addAttribute("error", "Game does not exist.");
        } catch (RoomNotInMapException e) {
            redirectAttributes.addAttribute("error", "Room not in maze.");
        } catch (NoKeyToCollectException e) {
            redirectAttributes.addAttribute("error", "No key to collect.");
        } catch (NotEnoughtFundsException e) {
            redirectAttributes.addAttribute("error", "Not enough funds to get the key.");
            return "redirect:/maze";
        } catch (RoomDoesNotExistException e) {
            redirectAttributes.addAttribute("error", "Room does not exist.");
        } catch (RoomConnectionDoesNotExist e) {
            redirectAttributes.addAttribute("error", "Room connection does not exist.");
        }
        return "redirect:/errorDisplay";
    }
}
