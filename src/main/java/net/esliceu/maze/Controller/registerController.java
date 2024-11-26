package net.esliceu.maze.Controller;

import jakarta.servlet.http.HttpSession;
import net.esliceu.maze.Exceptions.EmptyNameException;
import net.esliceu.maze.Exceptions.PasswordTooShortException;
import net.esliceu.maze.Exceptions.UsernameInUseException;
import net.esliceu.maze.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class registerController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    userService userService;
    @GetMapping("/register")
    public String getRegister(){
        if(httpSession.getAttribute("user") != null){
            return "redirect:/";
        }
        return "loginRegisterController";
    }
    @PostMapping("/register")
    public String postRegister(Model model, @RequestParam String name, @RequestParam String password) throws IOException{
        try {
            userService.register(name, password);
            return "redirect:/login";
        } catch (UsernameInUseException e) {
            model.addAttribute("error", "Name already in use.");
        } catch (PasswordTooShortException e) {
            model.addAttribute("error", "The password is too short");
        } catch (EmptyNameException e) {
            model.addAttribute("error", "Name is empty");
        }
        return "loginRegisterController";
    }
}
