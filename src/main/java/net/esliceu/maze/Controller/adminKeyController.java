package net.esliceu.maze.Controller;

import jakarta.servlet.http.HttpSession;
import net.esliceu.maze.Exceptions.KeyDoesNotExistException;
import net.esliceu.maze.Model.keyItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import net.esliceu.maze.Service.mazeComponentsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class adminKeyController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    mazeComponentsService mazeComponentsService;
    @GetMapping("/adminKey")
    public String getAdminKey(Model model){
        model.addAttribute("keys", mazeComponentsService.getAllKeyItems());
        return "keys";
    }
    @PostMapping("/adminDelKey")
    public String postAdminDelKey(RedirectAttributes redirectAttributes, @RequestParam int keyId){
        try {
            mazeComponentsService.deleteKey(keyId);
            return "redirect:/adminKey";
        } catch (KeyDoesNotExistException e) {
            redirectAttributes.addAttribute("error", "Key does not exist.");
        }
        return "redirect:/error";
    }

    @GetMapping("/adminUpdateKey")
    public String getAdminUpdateKey(RedirectAttributes redirectAttributes, Model model, @RequestParam int id){
        try {
            keyItem keyItem = mazeComponentsService.getKeyItem(id);
            model.addAttribute("cost", keyItem.getCost());
            model.addAttribute("id", keyItem.getId());
            model.addAttribute("name", keyItem.getName());
            return "keyForm";
        } catch (KeyDoesNotExistException e) {
            redirectAttributes.addAttribute("error", "Key does not exist.");
        }
        return "redirect:/error";
    }
    @PostMapping("/adminUpdateKey")
    public String postAdminUpdateKey(RedirectAttributes redirectAttributes, @RequestParam int id, @RequestParam String name, @RequestParam int cost){
        try {
            mazeComponentsService.updateKey(id, name, cost);
            return "redirect:/adminKey";
        } catch (KeyDoesNotExistException e) {
            redirectAttributes.addAttribute("error", "Key does not exist.");
        }
        return "redirect:/error";
    }

    @GetMapping("/adminAddKey")
    public String getAdminAddKey(){
        return "keyForm";
    }

    @PostMapping("/adminAddKey")
    public String postAdminAddKey(RedirectAttributes redirectAttributes, @RequestParam String name, @RequestParam int cost){
        mazeComponentsService.createKey(name, cost);
        return "redirect:/adminKey";
    }
}
