package net.esliceu.maze.Controller;

import jakarta.servlet.http.HttpSession;
import net.esliceu.maze.Exceptions.MapDoesNotExistDirection;
import net.esliceu.maze.Model.game;
import net.esliceu.maze.Service.gameHandlerService;
import net.esliceu.maze.Service.mazeComponentsService;
import net.esliceu.maze.Utils.score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class resultController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    gameHandlerService gameHandlerService;
    @Autowired
    mazeComponentsService mazeComponentsService;
    @GetMapping("/end")
    public String getEnd(Model model, @RequestParam String time) throws IOException {
        model.addAttribute("time", time);
        return "endForm";
    }
    @GetMapping("/scores")
    public String getScores(Model model, @RequestParam int id) throws IOException{
        List<score> scores = gameHandlerService.getFinishedGamesByMap(id);
        try {
            model.addAttribute(",map", mazeComponentsService.getMap(id).getName());
        } catch (MapDoesNotExistDirection e) {
           return "redirect:/error";
        }
        model.addAttribute("scores", scores);
        return "scores";
    }
}
