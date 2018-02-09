package ba.fit.java.spring.mvc.controllers;

import ba.fit.java.spring.mvc.entitymodels.*;
import ba.fit.java.spring.mvc.viewmodels.OdjeljenjeDetaljiVM;
import ba.fit.java.spring.mvc.viewmodels.OdjeljenjeDodajVM;
import ba.fit.java.spring.mvc.viewmodels.OdjeljenjeIndexVM;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequestMapping("/odjeljenje")
@Controller

    public class OdjeljenjeController
    {
        @PersistenceContext
        private EntityManager em;

        @RequestMapping(value = "/index")
        public ModelAndView index()
        {
            OdjeljenjeIndexVM model = new OdjeljenjeIndexVM();

            List<Odjeljenje> odjeljenja = em.createQuery("select x from Odjeljenje x", Odjeljenje.class).getResultList();

            model.rows = odjeljenja.stream().map(
                            a -> {
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
                            }
                    ).collect(toList());

            return new ModelAndView("odjeljenje/index", "model", model);
        }

        @Transactional
        @RequestMapping("obrisi")
        public String obrisi(int id)
        {
            Odjeljenje x = em.find(Odjeljenje.class, id);
            em.remove(x);
            return "redirect:/odjeljenje/index";
        }

        @RequestMapping("/provjeri-oznaku")
        @ResponseBody
        public String provjeriOznaku(String oznaka, String skolaGodina)
        {
            int c = em.createQuery("select count(x) from Odjeljenje x where x.oznaka = :oznaka and x.skolskaGodina = :skolaGodina")
                    .setParameter("oznaka", oznaka)
                    .setParameter("skolaGodina", skolaGodina)
                    .getFirstResult();
            if (c>0)
                return JSONObject.quote( "Oznaka " + oznaka + " je zauzeta za ?k. godinu " + skolaGodina);

            return JSONObject.quote("true");
        }


        @RequestMapping(value = "/dodaj")
        public ModelAndView dodaj(HttpServletRequest request, HttpServletResponse response)
        {
            OdjeljenjeDodajVM model = new OdjeljenjeDodajVM();

            KorisnickiNalog logiraniKorisnik = (KorisnickiNalog) request.getSession().getAttribute("logiraniKorisnik");
            if (logiraniKorisnik != null)
                model.nastavnik = logiraniKorisnik.getKorisnickoIme();

            pripremiCmbStavke(model);

            return new ModelAndView("odjeljenje/dodaj", "model", model);
        }
        @Transactional
        @RequestMapping(value = "/snimi")
        public String snimi(@ModelAttribute("model") OdjeljenjeDodajVM input)
        {

//            if (!ModelState.IsValid)
//            {
//                pripremiCmbStavke(input);
//                return View("Dodaj", input);
//            }

            Nastavnik nastavnik = em.createQuery("select  x from Nastavnik x", Nastavnik.class)
                    .setMaxResults(1)
                    .getSingleResult();


//            Nastavnik nastavnik = em.createQuery("select x from Nastavnik x where x.korisnickiNalog.id = :korisnikId", Nastavnik.class)
//                    .setParameter("korisnikId", 2)
//                    .getSingleResult();

            Odjeljenje o2 = new Odjeljenje(input.skolaGodina, input.razred, input.oznaka, nastavnik);
            em.persist(o2);

            Odjeljenje o1 = em.find(Odjeljenje.class, input.odjeljenjeID);

            if (o1 != null)
            {
                o1.setPrebacenuViseOdjeljenje(true);
                int b = 0;

                List<OdjeljenjeStavka> s1s = em.createQuery("select x from OdjeljenjeStavka x where x.odjeljenje.id = :o1Id", OdjeljenjeStavka.class)
                        .setParameter("o1Id", o1.getId())
                        .getResultList();

                for (OdjeljenjeStavka s1 : s1s)
                {
                    int brojNegativnihOcjena = em.createQuery("select count(x) from DodjeljenPredmet x where x.odjeljenjeStavka.id = :s1Id")
                            .setParameter("s1Id", s1.getId())
                            .getFirstResult();

                    if (brojNegativnihOcjena == 0)
                    {
                        OdjeljenjeStavka s2 = new OdjeljenjeStavka(s1.getUcenik(), o2, ++b);
                        em.persist(s2);

                        List<Predmet> predmeti = em.createQuery("select x from Predmet x where x.razred = :o2Razred", Predmet.class)
                                .setParameter("o2Razred", o2.getRazred())
                                .getResultList();
                        for (Predmet x : predmeti)
                        {
                            DodjeljenPredmet dp= new DodjeljenPredmet(s2, x, 0, 0);
                            em.persist(dp);
                        }
                    }
                }
            }

            return "redirect:/odjeljenje/index";
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


        private void pripremiCmbStavke(OdjeljenjeDodajVM ulazniModel)
        {
            List<Object[]> resultList = em.createQuery("select x.id, CONCAT(x.skolskaGodina, ' ', x.oznaka) from Odjeljenje x where x.isPrebacenuViseOdjeljenje = false", Object[].class).getResultList();
            ulazniModel.odjeljenja = new HashMap<>();
                    resultList.forEach(x->ulazniModel.odjeljenja.put((Integer)x[0], (String)x[1]));
        }
    }
