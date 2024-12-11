package net.esliceu.maze.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class errorController implements ErrorController {
    @RequestMapping("/errorDisplay")
    public String error(Model model, @RequestParam String error) {
        model.addAttribute("error", error);
        return "errorPage";
    }

    @RequestMapping("/error")
    public String defaultError() {
        return "errorPage";
    }

}
