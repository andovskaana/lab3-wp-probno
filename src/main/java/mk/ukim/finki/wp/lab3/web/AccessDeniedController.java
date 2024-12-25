package mk.ukim.finki.wp.lab3.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessDeniedController {

    @GetMapping("/access_denied")
    public String accessDeniedPage(Model model) {
        // Add any additional data to the model if needed
        model.addAttribute("message", "You are not authorized to view this page.");
        return "access_denied"; // This will render access-denied.html
    }
}

