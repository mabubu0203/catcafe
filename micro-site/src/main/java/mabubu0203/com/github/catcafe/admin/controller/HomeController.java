package mabubu0203.com.github.catcafe.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Deprecated
class HomeController {

    @GetMapping("/")
    String home(Model model) {
        return "home";
    }

}
