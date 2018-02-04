package ba.fit.java.spring.mvc.controllers;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GuestController {
 


    @GetMapping("/")
    public String index(Model model) {
            model.addAttribute("message", "You are logged in as ");
        return "index";
    }

    @GetMapping("/loginAction")
    public String loginAction(Model model) {
        model.addAttribute("message", "You are logged in as ");
        return "index";
    }
}