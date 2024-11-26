package net.esliceu.maze.Controller;

import jakarta.servlet.http.HttpSession;
import net.esliceu.maze.Exceptions.IncorrectLoginException;
import net.esliceu.maze.Model.user;
import net.esliceu.maze.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class loginController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    userService userService;
    @GetMapping("/login")
    public String getLogin() throws IOException{
        if(httpSession.getAttribute("user") != null){
            return "redirect:/";
        }
        return "loginRegisterForm";
    }
    @PostMapping("/login")
    public String postLogin(Model model, @RequestParam String name, @RequestParam String password) throws IOException{
        try {
            user user = userService.login(name, password);
            httpSession.setAttribute("user", user);
            return "redirect:/";
        } catch (IncorrectLoginException e) {
            model.addAttribute("error", "Name and Password don't match");
        }
        return "loginRegisterForm";
    }

}
