package ba.fit.java.spring.mvc.helper;

import ba.fit.java.spring.mvc.entitymodels.AutorizacijskiToken;
import ba.fit.java.spring.mvc.entitymodels.KorisnickiNalog;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

import static java.util.Arrays.stream;

public class Autentifikacija {
    public static void setLogiraniKorisnik(HttpServletRequest request, HttpServletResponse response, KorisnickiNalog korisnickiNalog)
    {
        WebApplicationContext webAppCtx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        LocalContainerEntityManagerFactoryBean managerFactoryBean = webAppCtx.getBean(LocalContainerEntityManagerFactoryBean.class);
        EntityManager em = managerFactoryBean.getNativeEntityManagerFactory().createEntityManager();

        em.getTransaction().begin();
        String oldStringToken = getTrenutniToken(request);
        if (oldStringToken != null && !oldStringToken.isEmpty())
        {
            AutorizacijskiToken autorizacijskiToken = em.createQuery("select x from AutorizacijskiToken x where x.vrijednost = :token", AutorizacijskiToken.class)
                    .setParameter("token", oldStringToken)
                    .setMaxResults(1)
                    .getResultList()
                    .stream().collect(MyCollectors.singleOrDefault());

            if (autorizacijskiToken != null)
            {
                em.remove(autorizacijskiToken);
            }
        }
        if (korisnickiNalog != null)
        {
            String newStringToken = UUID.randomUUID().toString();

            AutorizacijskiToken x = new AutorizacijskiToken();
            x.setVrijemeEvidentiranja(new Date());
            x.setKorisnickiNalog(korisnickiNalog);
            x.setIpAdresa(request.getRemoteAddr());
            x.setVrijednost(newStringToken);
            em.persist(x);

            setTrenutniToken(request, response, newStringToken);
        }
        em.getTransaction().commit();
        em.close();
    }

    public static KorisnickiNalog getLogiraniKorisnik(HttpServletRequest request) {

        WebApplicationContext webAppCtx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        LocalContainerEntityManagerFactoryBean managerFactoryBean = webAppCtx.getBean(LocalContainerEntityManagerFactoryBean.class);
        EntityManager em = managerFactoryBean.getNativeEntityManagerFactory().createEntityManager();

        String token = getTrenutniToken(request);
        if (token ==null || token.isEmpty())
            return null;

        KorisnickiNalog k = em.createQuery("select x.korisnickiNalog from AutorizacijskiToken x where x.vrijednost = :token", KorisnickiNalog.class)
                .setParameter("token", token)
                .setMaxResults(1)
                .getResultList()
                .stream().collect(MyCollectors.singleOrDefault());

        em.close();
        return k;
    }


    public static void setTrenutniToken(HttpServletRequest request, HttpServletResponse response, String value)
    {
        request.getSession(true).setAttribute("logirani", value);

        Cookie ck=new Cookie("token",value);//creating cookie object
        ck.setMaxAge(60*60*24);
        ck.setMaxAge(60*60*24);
        response.addCookie(ck);//adding cookie in the response
    }

    public static String getTrenutniToken(HttpServletRequest request) {

        String value = (String) request.getSession(true).getAttribute("logirani");

        if (value == null)
        {
            Cookie cookie = stream(request.getCookies()).filter(s -> s.getName().equals("token")).collect(MyCollectors.firstOrDefault());
            if (cookie!=null)
            {
                value = cookie.getValue();
            }
        }
        return value;
    }
}
