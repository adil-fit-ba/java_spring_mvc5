package ba.fit.java.spring.mvc.controllers.ucenik;

import ba.fit.java.spring.mvc.helper.filter.MyAutorizationAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestMapping("/ucenik-home")
@MyAutorizationAttribute(isNastavnik = false, isUcenik = true)
@Controller

    public class UcenikHomeController
    {
        @PersistenceContext
        private EntityManager em;

        @RequestMapping(value = "/*")
        public ModelAndView index()
        {

            return new ModelAndView("ucenik/home");
        }


    }
