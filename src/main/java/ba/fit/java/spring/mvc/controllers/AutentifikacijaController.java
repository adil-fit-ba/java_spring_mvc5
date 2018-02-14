package ba.fit.java.spring.mvc.controllers;

import ba.fit.java.spring.mvc.entitymodels.*;
import ba.fit.java.spring.mvc.viewmodels.CheckBoxValidator;
import ba.fit.java.spring.mvc.viewmodels.LoginVM;
import ba.fit.java.spring.mvc.viewmodels.StavkeDodajVM;
import ba.fit.java.spring.mvc.viewmodels.StavkeIndexVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequestMapping("/autentifikacija")
@Controller
    public class AutentifikacijaController
{

    @Autowired
    private CheckBoxValidator userValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }


    @PersistenceContext
    private EntityManager em;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index()
    {
        LoginVM model = new LoginVM();

        return new ModelAndView("autentifikacija/index", "model", model);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute("model") LoginVM input,  BindingResult result)
    {
        if (result.hasErrors())
            return new ModelAndView("autentifikacija/index", "model", input);

        List<KorisnickiNalog> resultList = em.createQuery("select x from KorisnickiNalog x where x.korisnickoIme=:u and x.lozinka=:p", KorisnickiNalog.class)
                .setParameter("u", input.username)
                .setParameter("p", input.password)
                .getResultList();

        if (resultList.size() == 0)
            return new ModelAndView("autentifikacija/index", "model", input);


        KorisnickiNalog korisnik  = resultList.get(0);

        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout()
    {

        return new ModelAndView("redirect:/");
    }
}
