package ba.fit.java.spring.mvc.controllers;
 
import ba.fit.java.spring.mvc.dao.MojDBInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Controller
public class GuestController {


    @PersistenceContext
    private EntityManager _context;

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
    @GetMapping("/TestDB")
    @Transactional(readOnly = false)
    public String TestDB()
    {
        MojDBInitializer.Izvrsi(_context);
        return "redirect:/";
    }
}