package ba.fit.java.spring.mvc.controllers;


import ba.fit.java.spring.mvc.viewmodels.OdjeljenjeIndexVM;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView index()
    {
        return new ModelAndView("index");
    }

    @GetMapping("/preusmjeri")
    public ModelAndView preusmjeri()
    {
        return new ModelAndView("redirect:/odjeljenja");
    }


    @GetMapping("/odjeljenja")
    public ModelAndView odjeljenja(String trazi)
    {
        OdjeljenjeIndexVM model = new OdjeljenjeIndexVM();
        List<OdjeljenjeIndexVM.Row> podaci = new ArrayList<>();
        podaci.add(new OdjeljenjeIndexVM.Row (4.5, "Meho Mehic", "2015/16", 2, "II-a", "prof. Zanin Vejzovic", false, 1));
        podaci.add(new OdjeljenjeIndexVM.Row (4.4, "Ada Adic", "2015/16", 2, "II-b", "prof. Jasmin Azemovic", false, 1));

        model.rows = podaci.stream().filter(w->trazi == null || trazi.length()==0 || w.oznaka.startsWith(trazi)).collect(Collectors.toList());

        return new ModelAndView("odjeljenja", "model", model);
    }

}
