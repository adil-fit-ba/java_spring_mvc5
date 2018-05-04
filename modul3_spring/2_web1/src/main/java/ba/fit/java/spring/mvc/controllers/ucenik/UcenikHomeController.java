package ba.fit.java.spring.mvc.controllers.ucenik;

import ba.fit.java.spring.mvc.entitymodels.*;
import ba.fit.java.spring.mvc.filter.MyAutorizationAttribute;
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

            return new ModelAndView("odjeljenje/index");
        }


    }
