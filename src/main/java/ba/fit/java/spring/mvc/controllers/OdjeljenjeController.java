package ba.fit.java.spring.mvc.controllers;

import ba.fit.java.spring.mvc.entitymodels.KorisnickiNalog;
import ba.fit.java.spring.mvc.entitymodels.Odjeljenje;
import ba.fit.java.spring.mvc.helper.SelectListItem;
import ba.fit.java.spring.mvc.viewmodels.OdjeljenjeDodajVM;
import ba.fit.java.spring.mvc.viewmodels.OdjeljenjeIndexVM;
import com.sun.net.httpserver.HttpContext;
import javassist.compiler.ast.Pair;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RequestMapping("Odjeljenje")
@Controller

    public class OdjeljenjeController
    {
        @PersistenceContext
        private EntityManager em;

        @RequestMapping("Index")
        public String Index(Model model)
        {
            OdjeljenjeIndexVM x = new OdjeljenjeIndexVM();

            List<Odjeljenje> odjeljenja = em.createQuery("select x from Odjeljenje x", Odjeljenje.class).getResultList();

            x.rows = odjeljenja.stream().map(
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

            model.addAttribute("model", x);

            return "Odjeljenje/index";
        }

        @RequestMapping("Obrisi")
        public String Obrisi(int id)
        {
            Odjeljenje x = em.find(Odjeljenje.class, id);
            em.remove(x);
            return "Odjeljenje/Index";
        }
        @RequestMapping("Dodaj")
        public String Dodaj(Model model, HttpServletRequest request, HttpServletResponse response)
        {
            OdjeljenjeDodajVM x = new OdjeljenjeDodajVM();

            KorisnickiNalog logiraniKorisnik = (KorisnickiNalog) request.getSession().getAttribute("logiraniKorisnik");
            if (logiraniKorisnik != null)
                x.nastavnik = logiraniKorisnik.getKorisnickoIme();

            pripremiCmbStavke(x);
            model.addAttribute("model", x);

            return "Dodaj";
        }

        private void pripremiCmbStavke(OdjeljenjeDodajVM ulazniModel)
        {
            ulazniModel.odjeljenja = em.createQuery("select new ba.fit.java.spring.mvc.helper.SelectListItem(x.id, CONCAT(x.skolskaGodina, ' ', x.oznaka)) from Odjeljenje x where x.isPrebacenuViseOdjeljenje = false", SelectListItem.class).getResultList();
        }
    }
