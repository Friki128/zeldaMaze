package net.esliceu.maze.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class errorController {
    static String message;
    @GetMapping("/error")
    public String error(Model model){
        model.addAttribute("error", message);
        return "errorPage";
    }
    public static void setMessage(String newMessage){
        message = newMessage;
    }

}
