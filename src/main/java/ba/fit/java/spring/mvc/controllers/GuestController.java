package ba.fit.java.spring.mvc.controllers;
 
import ba.fit.java.spring.mvc.dao.KorisnikDAO;
import ba.fit.java.spring.mvc.dao.MojDBInitializer;
import ba.fit.java.spring.mvc.entitymodels.KorisnickiNalog;
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


    @Autowired
    private KorisnikDAO userDao;

    @Autowired
    private MojDBInitializer dbInitializer;

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
    public String TestDB()
    {
        dbInitializer.Izvrsi();

        userDao.saveUserDetail(new KorisnickiNalog("a", "b"));

        return "redirect:/";
    }
}