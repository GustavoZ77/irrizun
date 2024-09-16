package zuun.tech.budget.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "home/login";
    }

    @GetMapping("/home")
    public String homePage() {
        return "home/home";
    }
}
