package net.esliceu.maze.Controller;

import net.esliceu.maze.Exceptions.KeyDoesNotExistException;
import net.esliceu.maze.Exceptions.RoomConnectionDoesNotExist;
import net.esliceu.maze.Exceptions.RoomDoesNotExistException;
import net.esliceu.maze.Model.keyItem;
import net.esliceu.maze.Model.map;
import net.esliceu.maze.Model.room;
import net.esliceu.maze.Model.roomMap;
import net.esliceu.maze.Service.mazeComponentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class adminRoomMapController {
    @Autowired
    mazeComponentsService mazeComponentsService;
    @GetMapping("/adminRoomMap")
    public String getAdminKey(Model model, @RequestParam int mapId){
        model.addAttribute("roomMaps", mazeComponentsService.getRoomMapByMap(mapId));
        return "roomMaps";
    }
    @PostMapping("/adminDelRoomMap")
    public String postAdminDelRoomMap(RedirectAttributes redirectAttributes, @RequestParam int roomMapId){
        try {
            mazeComponentsService.removeRoomToMap(roomMapId);
            return "redirect:/adminRoomMap";
        } catch (RoomConnectionDoesNotExist e) {
            redirectAttributes.addAttribute("error", "Room does not exist in map.");
        }
        return "redirect:/error";
    }

    @GetMapping("/adminUpdateRoomMap")
    public String getAdminUpdateRoomMap(RedirectAttributes redirectAttributes, Model model, @RequestParam int id){
        try {
            roomMap roomMap = mazeComponentsService.getRoomMap(id);
            room room = mazeComponentsService.getRoom(roomMap.getRoom());
            model.addAttribute("id", roomMap.getId());
            model.addAttribute("name", roomMap.getName());
            model.addAttribute("upConnection", roomMap.getUpDirection());
            model.addAttribute("downConnection", roomMap.getDownDirection());
            model.addAttribute("leftConnection", roomMap.getLeftDirection());
            model.addAttribute("rightConnection", roomMap.getRightDirection());
            model.addAttribute("upStatus", room.getUpDirection());
            model.addAttribute("downStatus", room.getDownDirection());
            model.addAttribute("leftStatus", room.getLeftDirection());
            model.addAttribute("rightStatus", room.getRightDirection());
            model.addAttribute("coin", room.getCoinPosition());
            model.addAttribute("key", room.getKeyPosition());
            return "roomMapForm";
        } catch (RoomConnectionDoesNotExist e) {
            redirectAttributes.addAttribute("error", "Room does not exist in map.");
        } catch (RoomDoesNotExistException e) {
            redirectAttributes.addAttribute("error", "Room does not exist.");
        }
        return "redirect:/error";
    }
    @PostMapping("/adminUpdateRoomMap")
    public String postAdminUpdateRoomMap(RedirectAttributes redirectAttributes, @RequestParam int id, @RequestParam String name, @RequestParam int up, @RequestParam int left, @RequestParam int down, @RequestParam int right){
        try {
            mazeComponentsService.updateRoomMap(id, up, down, left, right, name);
            return "redirect:/adminRoomMap";
        } catch (RoomConnectionDoesNotExist e) {
            redirectAttributes.addAttribute("error", "Room does not exist in map.");
        }
        return "redirect:/error";
    }

    @GetMapping("/adminAddRoomMap")
    public String getAdminAddRoomMap(){
        return "roomMapForm";
    }

    @PostMapping("/adminAddRoomMap")
    public String postAdminAddRoomMap(RedirectAttributes redirectAttributes, @RequestParam int room, @RequestParam int map, @RequestParam String name){
        mazeComponentsService.addRoomToMap(room, map, name);
        return "redirect:/adminRoomMap";
    }
}
