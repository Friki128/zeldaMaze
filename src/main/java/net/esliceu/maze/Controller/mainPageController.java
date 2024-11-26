package net.esliceu.maze.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class mainPageController {
    @Autowired
    HttpSession httpSession;
    @GetMapping("/")
    public String mainPage() throws IOException{
        return "mainPage";
    }
}
