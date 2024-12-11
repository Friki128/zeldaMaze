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
    public String getAdminRoomMap(Model model, @RequestParam int mapId){
        model.addAttribute("type", "RoomMap");
        model.addAttribute("map", mapId);
        model.addAttribute("values", mazeComponentsService.getRoomMapByMap(mapId));
        return "adminList";
    }
    @GetMapping("/adminDelRoomMap")
    public String getAdminDelRoomMap(RedirectAttributes redirectAttributes, @RequestParam int id){
        try {
            roomMap roomMap = mazeComponentsService.getRoomMap(id);
            redirectAttributes.addAttribute("mapId", roomMap.getMap());
            mazeComponentsService.removeRoomToMap(id);
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
            model.addAttribute("type", "Update");
            model.addAttribute("id", roomMap.getId());
            model.addAttribute("name", roomMap.getName());
            model.addAttribute("up", roomMap.getUpDirection());
            model.addAttribute("down", roomMap.getDownDirection());
            model.addAttribute("left", roomMap.getLeftDirection());
            model.addAttribute("right", roomMap.getRightDirection());
            model.addAttribute("rooms", mazeComponentsService.getRoomMapByMap(roomMap.getMap()));
            return "roomMapForm";
        } catch (RoomConnectionDoesNotExist e) {
            redirectAttributes.addAttribute("error", "Room does not exist in map.");
        }
        return "redirect:/error";
    }
    @PostMapping("/adminUpdateRoomMap")
    public String postAdminUpdateRoomMap(RedirectAttributes redirectAttributes, @RequestParam int id, @RequestParam String name, @RequestParam int up, @RequestParam int left, @RequestParam int down, @RequestParam int right){
        try {
            mazeComponentsService.updateRoomMap(id, up, down, left, right, name);
            redirectAttributes.addAttribute("mapId", mazeComponentsService.getRoomMap(id).getMap());
            return "redirect:/adminRoomMap";
        } catch (RoomConnectionDoesNotExist e) {
            redirectAttributes.addAttribute("error", "Room does not exist in map.");
        }
        return "redirect:/error";
    }

    @GetMapping("/adminAddRoomMap")
    public String getAdminAddRoomMap(Model model, @RequestParam int map){
        model.addAttribute("map", map);
        model.addAttribute("type", "Add");
        model.addAttribute("rooms", mazeComponentsService.getAllRooms());
        return "roomMapForm";
    }

    @PostMapping("/adminAddRoomMap")
    public String postAdminAddRoomMap(RedirectAttributes redirectAttributes, @RequestParam int room, @RequestParam int map, @RequestParam String name){
        mazeComponentsService.addRoomToMap(room, map, name);
        redirectAttributes.addAttribute("mapId", map);
        return "redirect:/adminRoomMap";
    }
}
