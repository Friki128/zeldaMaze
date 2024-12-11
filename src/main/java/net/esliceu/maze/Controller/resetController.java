package net.esliceu.maze.Controller;

import jakarta.servlet.http.HttpSession;
import net.esliceu.maze.Exceptions.*;
import net.esliceu.maze.Model.user;
import net.esliceu.maze.Service.gameHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class resetController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    gameHandlerService gameHandlerService;
    @GetMapping("/reset")
    public String getReset(RedirectAttributes redirectAttributes){
        user user = (user) httpSession.getAttribute("user");
        try {
            gameHandlerService.resetGame(user);
            return "redirect:/maze";
        } catch (GameDoesNotExistException e) {
            redirectAttributes.addAttribute("error", "Game does not exist.");
        } catch (MapDoesNotExistDirection e) {
            redirectAttributes.addAttribute("error", "Map does not exist.");
        } catch (RoomDoesNotExistException e) {
            redirectAttributes.addAttribute("error", "Room does not exist.");
        } catch (RoomConnectionDoesNotExist e) {
            redirectAttributes.addAttribute("error", "Room connection does not exist.");
        } catch (RoomNotInMapException e) {
            redirectAttributes.addAttribute("error", "Room not in maze.");
        } catch (GameAlreadyInMotionException e) {
            redirectAttributes.addAttribute("error", "Game already in motion.");
        }
        return "redirect:/errorDisplay";
    }
}
