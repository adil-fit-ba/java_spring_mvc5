package ba.fit.java.spring.mvc.controllers;
 
import ba.fit.java.spring.mvc.entitymodels.AutorizacijskiToken;
import ba.fit.java.spring.mvc.entitymodels.KorisnickiNalog;
import ba.fit.java.spring.mvc.filter.MyAutorizationAttribute;
import ba.fit.java.spring.mvc.helper.Autentifikacija;
import ba.fit.java.spring.mvc.helper.MyCollectors;
import ba.fit.java.spring.mvc.viewmodels.Row;
import ba.fit.java.spring.mvc.viewmodels.SesijaIndexVM;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/sesija")
@MyAutorizationAttribute(isUcenik = true, isNastavnik = true)
public class SesijaController {


    @PersistenceContext
    private EntityManager em;


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest ctx)
    {
        SesijaIndexVM model = new SesijaIndexVM();
        KorisnickiNalog korisnik = Autentifikacija.getLogiraniKorisnik(ctx);

        model.rows =  em.createQuery("select new ba.fit.java.spring.mvc.viewmodels.Row(x.vrijemeEvidentiranja, x.vrijednost, x.ipAdresa) from AutorizacijskiToken x where x.korisnickiNalog.id = :kId", Row.class)
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