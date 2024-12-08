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

import java.io.IOException;

@Controller
public class moveController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    gameHandlerService gameHandlerService;
    @GetMapping("/move")
    public String getMove(RedirectAttributes redirectAttributes, @RequestParam String dir) throws IOException{
        user user = (user) httpSession.getAttribute("user");
        try {
            String time = gameHandlerService.move(user, dir);
            if(!time.isEmpty()){
                redirectAttributes.addAttribute("time", time);
                return "redirect:/end";
            }
            return "redirect:/maze";
        } catch (GameDoesNotExistException e) {
            redirectAttributes.addAttribute("error", "Game does not exist.");
        } catch (RoomNotInMapException e) {
            redirectAttributes.addAttribute("error", "Room not in maze.");
        } catch (RoomConnectionDoesNotExist e) {
            redirectAttributes.addAttribute("error", "Room connection does not exist.");
        } catch (RoomDoesNotExistException e) {
            redirectAttributes.addAttribute("error", "Room does not exist.");
        } catch (InvalidDirectionException e) {
            redirectAttributes.addAttribute("error", "Invalid direction.");
        } catch (ClosedDoorException e) {
            redirectAttributes.addAttribute("error", "The door is closed.");
            return "redirect:/maze";
        } catch (NoDoorException e) {
            redirectAttributes.addAttribute("error", "There is no path there.");
            return "redirect:/maze";
        }
        return "redirect:/error";
    }
}
