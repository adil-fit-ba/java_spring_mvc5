package ba.fit.java.spring.mvc.controllers;

import ba.fit.java.spring.mvc.entitymodels.AutorizacijskiToken;
import ba.fit.java.spring.mvc.entitymodels.KorisnickiNalog;
import ba.fit.java.spring.mvc.helper.Autentifikacija;
import ba.fit.java.spring.mvc.helper.MyCollectors;
import ba.fit.java.spring.mvc.viewmodels.SesijaIndexRow;
import ba.fit.java.spring.mvc.viewmodels.SesijaIndexVM;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/sesija")

public class SesijaController {


    @PersistenceContext
    private EntityManager em;


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest ctx)
    {
        SesijaIndexVM model = new SesijaIndexVM();
        KorisnickiNalog korisnik = Autentifikacija.getLogiraniKorisnik(ctx);

        model.rows =  em.createQuery("select new ba.fit.java.spring.mvc.viewmodels.SesijaIndexRow(x.vrijemeEvidentiranja, x.vrijednost, x.ipAdresa) from AutorizacijskiToken x where x.korisnickiNalog.id = :kId", SesijaIndexRow.class)
                .setParameter("kId", korisnik.getId())
                .getResultList();

        return new ModelAndView("sesija/index", "model", model);
    }

    @RequestMapping(value = "/obrisi")
    @Transactional
    public ModelAndView obrisi(String value, HttpServletRequest ctx)
    {
        AutorizacijskiToken token = em.createQuery("select x from AutorizacijskiToken x where x.vrijednost = :token", AutorizacijskiToken.class)
        .setParameter("token", value)
                .getResultList()
                .stream()
                .collect(MyCollectors.singleOrDefault());

        if (token != null)
            em.remove(token);

        return new ModelAndView("redirect:/sesija/index");
    }
}