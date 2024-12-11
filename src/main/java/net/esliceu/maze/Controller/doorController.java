package net.esliceu.maze.Controller;

import jakarta.servlet.http.HttpSession;
import net.esliceu.maze.Exceptions.*;
import net.esliceu.maze.Model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import net.esliceu.maze.Service.gameHandlerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class doorController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    gameHandlerService gameHandlerService;
    @GetMapping("/open")
    public String getOpen(RedirectAttributes redirectAttributes, @RequestParam String dir){
        user user = (user) httpSession.getAttribute("user");
        try {
            gameHandlerService.openDoor(user, dir);
            return "redirect:/maze";
        } catch (GameDoesNotExistException e) {
            redirectAttributes.addAttribute("error", "Game does not exist.");
        } catch (RoomNotInMapException e) {
            redirectAttributes.addAttribute("error", "Room not in map.");
        } catch (RoomConnectionDoesNotExist e) {
            redirectAttributes.addAttribute("error", "Room connection does not exist.");
        } catch (RoomDoesNotExistException e) {
            redirectAttributes.addAttribute("error", "Room does not exist.");
        } catch (InvalidDirectionException e) {
            redirectAttributes.addAttribute("error", "The direction is invalid.");
        } catch (IncorrectKeyException e) {
            redirectAttributes.addAttribute("error", "The key does not match the keyhole.");
            return "redirect:/maze";
        }
        return "redirect:/errorDisplay";
    }

}
