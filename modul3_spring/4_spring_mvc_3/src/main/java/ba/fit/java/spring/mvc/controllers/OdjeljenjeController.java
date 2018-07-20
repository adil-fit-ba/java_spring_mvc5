package ba.fit.java.spring.mvc.controllers;


import ba.fit.java.spring.mvc.entitymodels.DodjeljenPredmet;
import ba.fit.java.spring.mvc.entitymodels.Nastavnik;
import ba.fit.java.spring.mvc.entitymodels.Odjeljenje;
import ba.fit.java.spring.mvc.entitymodels.OdjeljenjeStavka;
import ba.fit.java.spring.mvc.viewmodels.OdjeljenjeDodajVM;
import ba.fit.java.spring.mvc.viewmodels.OdjeljenjeIndexVM;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping("/odjeljenje")
public class OdjeljenjeController {


    @PersistenceContext
    private EntityManager em;

    @GetMapping("/index")
    public ModelAndView index(String trazi)
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

        return new ModelAndView("odjeljenje/index", "model", model);
    }

    @Transactional
    @RequestMapping("obrisi")
    public ModelAndView obrisi(int id) {

        List<DodjeljenPredmet> dodjeljeniPredmeti = em.createQuery("select x from DodjeljenPredmet x where x.odjeljenjeStavka.odjeljenje.id = :oId", DodjeljenPredmet.class)
                .setParameter("oId", id)
                .getResultList();

        for (DodjeljenPredmet x : dodjeljeniPredmeti) {
            em.remove(x);
        }

        List<OdjeljenjeStavka> stavke = em.createQuery("select x from OdjeljenjeStavka x where x.odjeljenje.id = :oId", OdjeljenjeStavka.class)
                .setParameter("oId", id)
                .getResultList();

        for (OdjeljenjeStavka x : stavke) {
            em.remove(x);
        }

        Odjeljenje x = em.find(Odjeljenje.class, id);
        em.remove(x);
        return new ModelAndView("redirect:/odjeljenje/index");
    }

    @RequestMapping("/dodaj")
    public ModelAndView dodaj()
    {
        OdjeljenjeDodajVM model = new OdjeljenjeDodajVM();
        model.odjeljenjeId = 0;
        return new ModelAndView("odjeljenje/dodaj", "model", model);

    }

    @RequestMapping("/uredi")
    public ModelAndView uredi(int id)
    {
        OdjeljenjeDodajVM model = new OdjeljenjeDodajVM();
        Odjeljenje x = em.find(Odjeljenje.class, id);

        model.oznaka=x.getOznaka();
        model.razred=x.getRazred();
        model.skolaGodina=x.getSkolskaGodina();
        model.nastavnik =x.getRazrednik().getIme() + " " + x.getRazrednik().getPrezime();
        model.odjeljenjeId = x.getId();
        return new ModelAndView("odjeljenje/dodaj", "model", model);

    }

    @Transactional
    @RequestMapping( "/snimi")
    public ModelAndView snimi(String skolaGodina, int razred, String oznaka, int odjeljenjeId )
    {
        Nastavnik nastavnik = em.find(Nastavnik.class, 1);

        Odjeljenje o;

        if (odjeljenjeId==0)
        {
            o = new Odjeljenje();

        }
        else
        {
            o = em.find(Odjeljenje.class, odjeljenjeId);
        }

        o.setOznaka(oznaka);
        o.setRazred(razred);
        o.setSkolskaGodina(skolaGodina);
        o.setRazrednik(nastavnik);

        if (odjeljenjeId==0)
        {
            em.persist(o);
        }

        return new ModelAndView("redirect:/odjeljenje/index");

    }
}
