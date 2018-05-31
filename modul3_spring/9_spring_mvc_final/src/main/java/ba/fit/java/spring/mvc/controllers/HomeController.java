package ba.fit.java.spring.mvc.controllers;
 
import ba.fit.java.spring.mvc.dao.MojDBInitializer;
import ba.fit.java.spring.mvc.viewmodels.HomeTestDbVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Controller
public class HomeController {

    @Autowired
    private MojDBInitializer dbInitializer;

    @PersistenceContext
    private EntityManager em;

    @GetMapping("/")
    public String index(Model model) {
        return "home/index";
    }

    @GetMapping("/test-db")
    public ModelAndView testDB()
    {
        dbInitializer.Izvrsi();

        HomeTestDbVM model = new HomeTestDbVM();
        model.ucenikCount = em.createQuery("select count(x) from Ucenik x", Long.class).getSingleResult();
        model.nastavnikCount = em.createQuery("select count(x) from Nastavnik x", Long.class).getSingleResult();
        model.odjeljenjeCount = em.createQuery("select count(x) from Odjeljenje x", Long.class).getSingleResult();
        model.predmetCount = em.createQuery("select count(x) from Predmet x", Long.class).getSingleResult();


        return new ModelAndView("home/testdb", "model", model);
    }
}