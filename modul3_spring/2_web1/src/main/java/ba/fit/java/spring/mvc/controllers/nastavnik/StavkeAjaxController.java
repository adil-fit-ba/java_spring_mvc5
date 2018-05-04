package ba.fit.java.spring.mvc.controllers.nastavnik;

import ba.fit.java.spring.mvc.entitymodels.DodjeljenPredmet;
import ba.fit.java.spring.mvc.entitymodels.Odjeljenje;
import ba.fit.java.spring.mvc.entitymodels.OdjeljenjeStavka;
import ba.fit.java.spring.mvc.entitymodels.Ucenik;
import ba.fit.java.spring.mvc.viewmodels.StavkeDodajVM;
import ba.fit.java.spring.mvc.viewmodels.StavkeIndexVM;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.HashMap;

import static java.util.stream.Collectors.toList;

@RequestMapping("/stavke-ajax")
@Controller
    public class StavkeAjaxController
{
    @PersistenceContext
    private EntityManager em;

    @RequestMapping(value = "/index")
    public ModelAndView Index(int odjeljenjeId)
    {
        StavkeIndexVM model = new StavkeIndexVM();
        model.OdjeljenjeId = odjeljenjeId;
        model.rows = em.createQuery("select x from OdjeljenjeStavka x where x.odjeljenje.id = :odjeljenjeId", OdjeljenjeStavka.class)
                .setParameter("odjeljenjeId", odjeljenjeId)
                .getResultList()
                .stream()
                .map(s -> {
                    StavkeIndexVM.Row row = new StavkeIndexVM.Row();
                    row.brojUDnevniku = s.getBrojUDnevniku();
                    row.brojZakljucihOcjena = em.createQuery("select count(x) from DodjeljenPredmet x where x.odjeljenjeStavka.id = :sId and x.zakljucnoKrajGodine <> 0").setParameter("sId", s.getId()).getFirstResult();
                    row.ucenik = s.getUcenik().getIme() + " " + s.getUcenik().getPrezime();
                    row.odjeljenjeStavkeId = s.getId();
                    return row;
                }).collect(toList());

        return new ModelAndView("stavke/index", "model", model);
    }

    @RequestMapping(value = "/obrisi")
    @Transactional
    public String obrisi(int id)
    {
        OdjeljenjeStavka x = em.find(OdjeljenjeStavka.class, id);
        int o = x.getOdjeljenje().getId();

        em.createQuery("select x from DodjeljenPredmet x where x.odjeljenjeStavka.id = :sId", DodjeljenPredmet.class)
                .setParameter("sId", x.getId())
                .getResultList()
                .stream()
                .forEach(e->em.remove(e));

        em.remove(x);
        return "redirect:/stavke-ajax/index?odjeljenjeId=" + o;

    }

    @RequestMapping(value = "/uredi")
    public ModelAndView uredi(int id)
    {
        OdjeljenjeStavka x = em.find(OdjeljenjeStavka.class, id);

        StavkeDodajVM model = new StavkeDodajVM();

        model.brojUdnevniku = x.getBrojUDnevniku();
        model.odjeljenjeID = x.getOdjeljenje().getId();
        model.odjeljenjeStavkaId = id;
        model.ucenikID = x.getUcenik().getId();

        model.ucenici = new HashMap<>();
        em.createQuery("select x from Ucenik x", Ucenik.class).getResultList().forEach(e -> model.ucenici.put(e.getId(), e.getIme() + " " + e.getPrezime()));

        return new ModelAndView("stavke/dodaj", "model", model);
    }

    @RequestMapping(value = "/dodaj")
    public ModelAndView dodaj(int odjeljenjeId)
    {
        StavkeDodajVM model = new StavkeDodajVM();
        model.brojUdnevniku = em.createQuery("select count(x) from OdjeljenjeStavka x where x.odjeljenje.id = :odjeljenjeId", Long.class).setParameter("odjeljenjeId", odjeljenjeId).getSingleResult().intValue() + 1;
        model.odjeljenjeID = odjeljenjeId;
        model.ucenikID = 0;

        model.ucenici = new HashMap<>();
        em.createQuery("select x from Ucenik x", Ucenik.class).getResultList().forEach(e -> model.ucenici.put(e.getId(), e.getIme() + " " + e.getPrezime()));

        return new ModelAndView("stavke/dodaj", "model", model);
    }

    @RequestMapping(value = "/snimi")
    @Transactional
    public ModelAndView snimi(@ModelAttribute("model") StavkeDodajVM input)
    {
        OdjeljenjeStavka x;

        if (input.odjeljenjeStavkaId == 0)
        {
            x = new OdjeljenjeStavka();
        }
        else
        {
            x = em.find(OdjeljenjeStavka.class, input.odjeljenjeStavkaId);
        }

        x.setOdjeljenje(em.find(Odjeljenje.class, input.odjeljenjeID));
        x.setUcenik(em.find(Ucenik.class, input.ucenikID));
        x.setBrojUDnevniku(input.brojUdnevniku);

        if (input.odjeljenjeStavkaId == 0)
            em.persist(x);

        return new ModelAndView("redirect:/stavke-ajax/index?odjeljenjeId=" + input.odjeljenjeID);
    }
}
