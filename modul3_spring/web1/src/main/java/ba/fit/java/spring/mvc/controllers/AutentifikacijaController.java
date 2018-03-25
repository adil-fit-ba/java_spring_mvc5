package ba.fit.java.spring.mvc.controllers;

import ba.fit.java.spring.mvc.entitymodels.*;
import ba.fit.java.spring.mvc.helper.Autentifikacija;
import ba.fit.java.spring.mvc.viewmodels.LoginVM;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/autentifikacija")
@Controller
    public class AutentifikacijaController
{


    @PersistenceContext
    private EntityManager em;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response)
    {
        Autentifikacija.setLogiraniKorisnik(request, response, null);

        LoginVM model = new LoginVM("nastavnik0", "test", true);

        return new ModelAndView("autentifikacija/login", "model", model);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute("model") LoginVM input,  BindingResult result, HttpServletRequest request, HttpServletResponse response)
    {
        if (result.hasErrors())
            return new ModelAndView("autentifikacija/login", "model", input);

        List<KorisnickiNalog> resultList = em.createQuery("select x from KorisnickiNalog x where x.korisnickoIme=:u and x.lozinka=:p", KorisnickiNalog.class)
                .setParameter("u", input.username)
                .setParameter("p", input.password)
                .getResultList();

        if (resultList.size() == 0)
            return new ModelAndView("autentifikacija/login", "model", input);


        KorisnickiNalog korisnik  = resultList.get(0);

       Autentifikacija.setLogiraniKorisnik(request, response, korisnik);

        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response)
    {
       Autentifikacija.setLogiraniKorisnik(request, response, null);
        return new ModelAndView("redirect:/autentifikacija/login");
    }
}
