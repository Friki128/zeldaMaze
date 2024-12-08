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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class startController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    gameHandlerService gameHandlerService;
    @PostMapping("/start")
    public String postStart(Model model, RedirectAttributes redirectAttributes, @RequestParam int mapId) throws IOException{
        user user = (user) httpSession.getAttribute("user");
        try {
            gameHandlerService.startGame(user, mapId);
            return "redirect:/maze";
        } catch (MapDoesNotExistDirection e) {
            redirectAttributes.addAttribute("error", "Map does not exist.");
        } catch (GameAlreadyInMotionException e) {
            redirectAttributes.addAttribute("error", "Game already in motion.");
        } catch (RoomDoesNotExistException e) {
            redirectAttributes.addAttribute("error", "Room does not exist.");
        } catch (RoomConnectionDoesNotExist e) {
            redirectAttributes.addAttribute("error", "Room connection does not exist.");
        } catch (RoomNotInMapException e) {
            redirectAttributes.addAttribute("error", "Room not in maze.");
        } catch (GameDoesNotExistException e) {
            redirectAttributes.addAttribute("error", "Game does not exist.");
        }
        return "redirect:/error";
    }
}
