package net.esliceu.maze.Controller;

import net.esliceu.maze.Exceptions.RoomDoesNotExistException;
import net.esliceu.maze.Model.room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import net.esliceu.maze.Service.mazeComponentsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class adminRoomController {
    @Autowired
    mazeComponentsService mazeComponentsService;
    @GetMapping("/adminRoom")
    public String getAdminRoom(Model model){
        model.addAttribute("type", "Room");
        model.addAttribute("values", mazeComponentsService.getAllRooms());
        return "adminList";
    }
    @GetMapping("/adminDelRoom")
    public String getAdminDelRoom(RedirectAttributes redirectAttributes, @RequestParam int id){
        try {
            mazeComponentsService.deleteRoom(id);
            return "redirect:/adminRoom";
        } catch (RoomDoesNotExistException e) {
            redirectAttributes.addAttribute("error", "Room does not exist.");
        }
        return "redirect:/error";
    }

    @GetMapping("/adminUpdateRoom")
    public String getAdminUpdateRoom(RedirectAttributes redirectAttributes, Model model, @RequestParam int id){
        try {
            room room = mazeComponentsService.getRoom(id);
            model.addAttribute("type", "Update");
            model.addAttribute("name", room.getName());
            model.addAttribute("id", room.getId());
            model.addAttribute("coinPosition", room.getCoinPosition());
            model.addAttribute("keyPosition", room.getKeyPosition());
            model.addAttribute("keyId", room.getKeyId());
            model.addAttribute("down", room.getDownDirection());
            model.addAttribute("up", room.getUpDirection());
            model.addAttribute("right", room.getRightDirection());
            model.addAttribute("left", room.getLeftDirection());
            model.addAttribute("keys", mazeComponentsService.getAllKeyItems());
            return "roomForm";
        } catch (RoomDoesNotExistException e) {
            redirectAttributes.addAttribute("error", "Room does not exist.");
        }
        return "redirect:/error";
    }
    @PostMapping("/adminUpdateRoom")
    public String postAdminUpdateRoom(RedirectAttributes redirectAttributes, @RequestParam int id, @RequestParam String name, @RequestParam String up, @RequestParam String right, @RequestParam String down, @RequestParam String left, @RequestParam int coin, @RequestParam int key, @RequestParam int keyId){
        try {
            mazeComponentsService.updateRoom(id, name, up, right, down, left, coin, key, keyId);
            return "redirect:/adminRoom";
        } catch (RoomDoesNotExistException e) {
            redirectAttributes.addAttribute("error", "Room does not exist.");
        }
        return "redirect:/error";
    }

    @GetMapping("/adminAddRoom")
    public String getAdminAddRoom(Model model){
        model.addAttribute("type", "Add");
        model.addAttribute("keys", mazeComponentsService.getAllKeyItems());
        return "roomForm";
    }

    @PostMapping("/adminAddRoom")
    public String postAdminAddRoom(RedirectAttributes redirectAttributes, @RequestParam String name, @RequestParam String up, @RequestParam String right, @RequestParam String down, @RequestParam String left, @RequestParam int coin, @RequestParam int key, @RequestParam int keyId){
        mazeComponentsService.createRoom(keyId, name, key, coin, up, down, right, left);
        return "redirect:/adminRoom";
    }
}
