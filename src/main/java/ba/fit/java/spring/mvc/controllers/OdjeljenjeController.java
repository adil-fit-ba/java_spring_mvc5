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
import java.util.ArrayList;
import java.util.List;

@Controller
    public class OdjeljenjeController
    {

        @Autowired
        private SessionFactory sessionFactory;

        @GetMapping("/Odjeljenje/Index")
        public String Index(Model model)
        {

            EntityManager session = sessionFactory.createEntityManager();
            OdjeljenjeIndexVM x = new OdjeljenjeIndexVM();
            x.rows = new ArrayList<>();

            List<Odjeljenje> odjeljenja = session.createQuery("select x from Odjeljenje x", Odjeljenje.class).getResultList();
            odjeljenja.forEach(a-> {
                OdjeljenjeIndexVM.Row row = new OdjeljenjeIndexVM.Row();

                row.isPrebacenuViseOdjeljenje = a.isPrebacenuViseOdjeljenje();
                row.odjeljenjeId=a.getId();
                row.oznaka = a.getOznaka();
                row.razred=a.getRazred();
                row.razrednik = a.getRazrednik().getIme() + " " + a.getRazrednik().getPrezime();
                row.skolskaGodina = a.getSkolskaGodina();

                x.rows.add(row);
            });

            model.addAttribute(x);

            session.close();

            return "Odjeljenje/Index";
        }

        public String Obrisi(int id)
        {

            return "Odjeljenje/Index";
        }


    }
