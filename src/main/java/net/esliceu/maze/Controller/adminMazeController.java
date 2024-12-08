package net.esliceu.maze.Controller;

import net.esliceu.maze.Exceptions.MapDoesNotExistDirection;
import net.esliceu.maze.Model.map;
import net.esliceu.maze.Service.mazeComponentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class adminMazeController {
    @Autowired
    mazeComponentsService mazeComponentsService;
    @GetMapping("/adminMaze")
    public String getAdminMaze(Model model){
        model.addAttribute("mazes", mazeComponentsService.getAllMaps());
        return "mazes";
    }
    @PostMapping("/adminDelMaze")
    public String postAdminDelMaze(RedirectAttributes redirectAttributes, @RequestParam int mazeId){
        try {
            mazeComponentsService.deleteMap(mazeId);
            return "redirect:/adminMaze";
        } catch (MapDoesNotExistDirection e) {
            redirectAttributes.addAttribute("error", "Maze does not exist.");
        }
        return "redirect:/error";
    }

    @GetMapping("/adminUpdateMaze")
    public String getAdminUpdateMaze(RedirectAttributes redirectAttributes, Model model, @RequestParam int id){
        try {
            map map = mazeComponentsService.getMap(id);
            model.addAttribute("id", map.getId());
            model.addAttribute("name", map.getName());
            model.addAttribute("startRoom", map.getStartRoom());
            return "mazeForm";
        } catch (MapDoesNotExistDirection e) {
            redirectAttributes.addAttribute("error", "Maze does not exist.");
        }
        return "redirect:/error";
    }
    @PostMapping("/adminUpdateMaze")
    public String postAdminUpdateMaze(RedirectAttributes redirectAttributes, @RequestParam int id, @RequestParam String name){
        try {
            mazeComponentsService.updateMapName(id, name);
            return "redirect:/adminMaze";
        } catch (MapDoesNotExistDirection e) {
            redirectAttributes.addAttribute("error", "Maze does not exist.");
        }
        return "redirect:/error";
    }

    @PostMapping("/adminUpdateMazeStart")
    public String postAdminUpdateMazeStart(RedirectAttributes redirectAttributes, @RequestParam int id, @RequestParam int start){
        try {
            mazeComponentsService.updateStartRoom(id, start);
        } catch (MapDoesNotExistDirection e) {
            redirectAttributes.addAttribute("error", "Maze does not exist.");
        }
        return "redirect:/error";
    }

    @GetMapping("/adminAddMaze")
    public String getAdminAddMaze(){
        return "mazeForm";
    }

    @PostMapping("/adminAddMaze")
    public String postAdminAddMaze(RedirectAttributes redirectAttributes, @RequestParam String name){
        mazeComponentsService.createMap(name);
        return "redirect:/adminMaze";
    }
}
