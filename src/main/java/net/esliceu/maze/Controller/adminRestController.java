package net.esliceu.maze.Controller;

import net.esliceu.maze.Model.keyItem;
import net.esliceu.maze.Model.roomMap;
import net.esliceu.maze.Model.room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import net.esliceu.maze.Service.mazeComponentsService;
import org.springframework.http.MediaType;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class adminRestController {
    @Autowired
    mazeComponentsService mazeComponentsService;
    @RequestMapping(value = "/adminGetKeys", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<keyItem> getKeys(){
        return mazeComponentsService.getAllKeyItems();
    }
    @RequestMapping(value = "/adminGetRoomMaps", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<roomMap> getRoomMaps(@RequestParam int mapId){
        return mazeComponentsService.getRoomMapByMap(mapId);
    }
    @RequestMapping(value = "/adminGetRooms", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<room> getRooms(){
        return mazeComponentsService.getAllRooms();
    }

}
