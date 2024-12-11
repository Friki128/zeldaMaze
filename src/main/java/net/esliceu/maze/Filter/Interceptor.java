package net.esliceu.maze.Filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.esliceu.maze.Model.user;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class Interceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        String uri = req.getRequestURI();
        user user = (user) req.getSession().getAttribute("user");
        if (uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".webp") || uri.endsWith(".gif")) {
            return true;
        }
        if((!(uri.equals("/login") || uri.equals("/register"))) && user == null) {
            resp.sendRedirect("/login");
        }
        if(uri.contains("admin")) {
            if (user != null && !user.isAdmin()) {
                resp.sendRedirect("/");
            }
        }
        return true;
    }
}
