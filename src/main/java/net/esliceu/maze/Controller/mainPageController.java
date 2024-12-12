package net.esliceu.maze.Controller;

import jakarta.servlet.http.HttpSession;
import net.esliceu.maze.Exceptions.GameDoesNotExistException;
import net.esliceu.maze.Model.user;
import net.esliceu.maze.Service.gameHandlerService;
import net.esliceu.maze.Service.mazeComponentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class mainPageController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    gameHandlerService gameHandlerService;
    @Autowired
    mazeComponentsService mazeComponentsService;
    @GetMapping("/")
    public String mainPage(Model model) throws IOException{
        user user = (user) httpSession.getAttribute("user");
        try {
            gameHandlerService.getCurrentGame(user.getId());
            return "redirect:/maze";
        } catch (GameDoesNotExistException e) {
            model.addAttribute("values", mazeComponentsService.getAllMaps());
            return "mazes";
        }
    }
    @GetMapping("/admin")
    public String adminPage(){
        return "adminPage";
    }
}
