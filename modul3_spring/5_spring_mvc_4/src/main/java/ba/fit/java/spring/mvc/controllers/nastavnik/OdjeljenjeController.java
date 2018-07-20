package ba.fit.java.spring.mvc.controllers.nastavnik;


import ba.fit.java.spring.mvc.entitymodels.*;
import ba.fit.java.spring.mvc.helper.filter.MyAutorizationAttribute;
import ba.fit.java.spring.mvc.viewmodels.OdjeljenjeDetaljiVM;
import ba.fit.java.spring.mvc.viewmodels.OdjeljenjeDodajVM;
import ba.fit.java.spring.mvc.viewmodels.OdjeljenjeIndexVM;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping("/odjeljenje")
@MyAutorizationAttribute(isNastavnik = true, isUcenik = false)
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
            row.brojUcenika=em.createQuery("select count(x) from OdjeljenjeStavka  x where x.odjeljenje.id  = :id ", Long.class)
                    .setParameter("id", a.getId())
                    .getSingleResult();
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
    public ModelAndView obrisi(int id, HttpServletRequest request, HttpServletResponse response) {

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
        popuniCmb(model);
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
        model.nastavnikId = x.getRazrednik().getId();
        model.odjeljenjeId = x.getId();


        popuniCmb(model);
        return new ModelAndView("odjeljenje/dodaj", "model", model);

    }

    private void popuniCmb(OdjeljenjeDodajVM model) {
        List<Object[]> odjeljenjaList = em.createQuery("select x.id, CONCAT(x.skolskaGodina, ' ', x.oznaka) from Odjeljenje x where x.isPrebacenuViseOdjeljenje = false", Object[].class)
                .getResultList();

        model.odjeljenja = new HashMap<>();

        odjeljenjaList.forEach(row -> model.odjeljenja.put((Integer) row[0], (String) row[1]));


        List<Object[]> nastavniciList = em.createQuery("select x.Id,  CONCAT(x.ime, ' ', x.prezime) from Nastavnik x", Object[].class)
                .getResultList();

        model.nastavnici = new HashMap<>();

        nastavniciList.forEach(row -> model.nastavnici.put((Integer) row[0], (String) row[1]));
    }

    @Transactional
    @RequestMapping( "/snimi")
    public ModelAndView snimi(@ModelAttribute("model") OdjeljenjeDodajVM ulaz )
    {
        Nastavnik nastavnik = em.find(Nastavnik.class, 1);

        Odjeljenje o2;

        if (ulaz.odjeljenjeId==0)
        {
            o2 = new Odjeljenje();

        }
        else
        {
            o2 = em.find(Odjeljenje.class, ulaz.odjeljenjeId);
        }

        o2.setOznaka(ulaz.oznaka);
        o2.setRazred(ulaz.razred);
        o2.setSkolskaGodina(ulaz.skolaGodina);
        o2.setRazrednik(nastavnik);

        if (ulaz.odjeljenjeId==0)
        {
            em.persist(o2);

            Odjeljenje o1 = em.find(Odjeljenje.class, ulaz.odjeljenjeId);

            if (o1 != null)
            {

                int b = 0;
                List<OdjeljenjeStavka> s1s = em.createQuery("select x from OdjeljenjeStavka x where x.odjeljenje.id = :o1Id", OdjeljenjeStavka.class)
                        .setParameter("o1Id", o1.getId())
                        .getResultList();

                for (OdjeljenjeStavka s1 : s1s)
                {


                    int brojNegativnihOcjena = em.createQuery("select count(x) from DodjeljenPredmet x where x.odjeljenjeStavka.id = :s1Id and x.zakljucnoKrajGodine = 1")
                            .setParameter("s1Id", s1.getId())
                            .getFirstResult();

                    if (brojNegativnihOcjena == 0)
                    {
                        OdjeljenjeStavka s2 = new OdjeljenjeStavka(s1.getUcenik(), o2, ++b);
                        em.persist(s2);


                        List<Predmet> predmeti = em.createQuery("select x from Predmet x where x.razred = :o2Razred", Predmet.class)
                                .setParameter("o2Razred", o2.getRazred())
                                .getResultList();

                        for (Predmet x : predmeti) {
                            DodjeljenPredmet dp = new DodjeljenPredmet(s2, x, 0, 0);
                            em.persist(dp);
                        }
                    }
                }
            }
        }

        return new ModelAndView("redirect:/odjeljenje/index");

    }

    @RequestMapping(value = "/detalji")
    public ModelAndView detalji(int id)
    {
        Odjeljenje o = em.find(Odjeljenje.class, id);

        OdjeljenjeDetaljiVM model = new OdjeljenjeDetaljiVM();

        model.razrednik = o.getRazrednik().getIme() + " " + o.getRazrednik().getPrezime();
        model.oznaka = o.getOznaka();
        model.razred = o.getRazred();
        model.skolskaGodina = o.getSkolskaGodina();
        model.odjeljenjeID = o.getId();

        model.brojPredmta = em.createQuery("select count(x) from Predmet  x where x.razred = :razred")
                .setParameter("razred", o.getRazred())
                .getFirstResult();

        model.brojUcenika = em.createQuery("select count(x) from OdjeljenjeStavka  x where x.odjeljenje.id = :id")
                .setParameter("id", id)
                .getFirstResult();

        return new ModelAndView("odjeljenje/detalji", "model", model);
    }

}
