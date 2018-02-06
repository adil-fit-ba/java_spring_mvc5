package ba.fit.java.spring.mvc.controllers;

import ba.fit.java.spring.mvc.entitymodels.KorisnickiNalog;
import ba.fit.java.spring.mvc.entitymodels.Odjeljenje;
import ba.fit.java.spring.mvc.viewmodels.OdjeljenjeIndexVM;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Controller
    public class OdjeljenjeController
    {

        @PersistenceContext
        private EntityManager em;

        @GetMapping("/Odjeljenje/Index")
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
                                row.prosjekOcjena = em.createQuery("select avg(x) from DodjeljenPredmet  x where x.odjeljenjeStavka.odjeljenje.id  = :id", float.class)
                                        .setParameter("id", a.getId())
                                        .getSingleResult();
                                return row;
                            }
                    ).collect(toList());

            model.addAttribute(x);

            return "Odjeljenje/index";
        }

        public String Obrisi(int id)
        {

            return "Odjeljenje/Index";
        }


    }
