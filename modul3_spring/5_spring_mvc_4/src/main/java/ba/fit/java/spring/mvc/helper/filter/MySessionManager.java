package ba.fit.java.spring.mvc.helper.filter;

import ba.fit.java.spring.mvc.entitymodels.KorisnickiNalog;
import ba.fit.java.spring.mvc.helper.Autentifikacija;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class MySessionManager extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof ResourceHttpRequestHandler)
            return true;

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();


        boolean isMyAutorizationAttribute = method.getDeclaringClass().isAnnotationPresent(MyAutorizationAttribute.class);
        if (!isMyAutorizationAttribute)
            return true;


        KorisnickiNalog korisnik = Autentifikacija.getLogiraniKorisnik(request);

        boolean imaPravoPristupa = false;
        if (korisnik != null) {

            WebApplicationContext webAppCtx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
            LocalContainerEntityManagerFactoryBean managerFactoryBean = webAppCtx.getBean(LocalContainerEntityManagerFactoryBean.class);
            EntityManager em = managerFactoryBean.getNativeEntityManagerFactory().createEntityManager();


            MyAutorizationAttribute a = method.getDeclaringClass().getAnnotation(MyAutorizationAttribute.class);
            if (a.isNastavnik())
            {
                Long c = em.createQuery("select count(x) from Nastavnik x where x.korisnickiNalog.id = :kId", Long.class)
                        .setParameter("kId", korisnik.getId())
                        .getSingleResult();

                if (c>0)
                    imaPravoPristupa = true;
            }

            if (a.isUcenik())
            {
                Long c = em.createQuery("select count(x) from Ucenik x where x.korisnickiNalog.id = :kId", Long.class)
                        .setParameter("kId", korisnik.getId())
                        .getSingleResult();

                if (c>0)
                    imaPravoPristupa = true;
            }
            em.close();
        }


        if (!imaPravoPristupa) {
            response.sendRedirect("/autentifikacija/login");
            return false;
        }


        return true;
    }
}