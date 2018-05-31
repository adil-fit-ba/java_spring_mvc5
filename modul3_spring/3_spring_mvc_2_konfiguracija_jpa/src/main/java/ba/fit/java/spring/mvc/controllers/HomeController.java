package ba.fit.java.spring.mvc.controllers;


import ba.fit.java.spring.mvc.dao.MojDBInitializer;
import ba.fit.java.spring.mvc.entitymodels.Odjeljenje;
import ba.fit.java.spring.mvc.viewmodels.HomeTestDbVM;
import ba.fit.java.spring.mvc.viewmodels.OdjeljenjeIndexVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView index()
    {
        return new ModelAndView("index");
    }

    @GetMapping("/preusmjeri")
    public ModelAndView preusmjeri()
    {
        return new ModelAndView("redirect:/odjeljenja");
    }



    @PersistenceContext
    private EntityManager em;

    @GetMapping("/test-db")
    @Transactional
    public ModelAndView testDB()
    {
        MojDBInitializer.Izvrsi(em);

        HomeTestDbVM model = new HomeTestDbVM();
        model.ucenikCount = em.createQuery("select count(x) from Ucenik x", Long.class).getSingleResult();
        model.nastavnikCount = em.createQuery("select count(x) from Nastavnik x", Long.class).getSingleResult();
        model.odjeljenjeCount = em.createQuery("select count(x) from Odjeljenje x", Long.class).getSingleResult();
        model.predmetCount = em.createQuery("select count(x) from Predmet x", Long.class).getSingleResult();


        return new ModelAndView("testdb", "model", model);
    }


    @GetMapping("/odjeljenja")
    public ModelAndView odjeljenja(String trazi)
    {
        OdjeljenjeIndexVM model = new OdjeljenjeIndexVM();


        List<Odjeljenje> odjeljenja = em.createQuery("select x from Odjeljenje x", Odjeljenje.class).getResultList();

        model.rows = odjeljenja.stream().map( a-> {
            OdjeljenjeIndexVM.Row row = new OdjeljenjeIndexVM.Row();
            row.isPrebacenuViseOdjeljenje = a.isPrebacenuViseOdjeljenje();
            row.odjeljenjeId=a.getId();
            row.oznaka = a.getOznaka();
            row.razred=a.getRazred();
            row.razrednik = a.getRazrednik().getIme() + " " + a.getRazrednik().getPrezime();
            row.skolskaGodina = a.getSkolskaGodina();
            row.prosjekOcjena = em.createQuery("select avg(x.zakljucnoKrajGodine) from DodjeljenPredmet  x where x.odjeljenjeStavka.odjeljenje.id  = :id and x.zakljucnoKrajGodine > 0 ", Double.class)
                    .setParameter("id", a.getId())
                    .getSingleResult();

            return row;
        }).collect(toList());

        return new ModelAndView("odjeljenja", "model", model);
    }

}
