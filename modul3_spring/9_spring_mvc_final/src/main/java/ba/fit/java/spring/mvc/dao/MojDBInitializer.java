package ba.fit.java.spring.mvc.dao;

import ba.fit.java.spring.mvc.entitymodels.*;
import ba.fit.java.spring.mvc.helper.MyJpaDao;
import org.hibernate.id.GUIDGenerator;
import org.hibernate.id.UUIDGenerator;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@Transactional(readOnly = true)
public class MojDBInitializer
{
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void Izvrsi()
    {
//        // Look for any students.
//        if (_context.createQuery("select count(x) from Ucenik x").getFirstResult() > 0)
//        {
//            return;   // DB has been seeded
//        }

        ArrayList<Ucenik> ucenici = new ArrayList<Ucenik>();
        ArrayList<Predmet> predmeti = new ArrayList<Predmet>();
        ArrayList<Odjeljenje> odjeljenja = new ArrayList<Odjeljenje>();
        ArrayList<Nastavnik> nastavniks = new ArrayList<Nastavnik>();

        for (int i = 0; i < 20; i++)
        {
            Nastavnik x = new Nastavnik("prof", "Nastavnik " + UUID.randomUUID().toString().substring(0, 3));
            x.setKorisnickiNalog(new KorisnickiNalog("nastavnik" + i, "test"));
            nastavniks.add(x);
            entityManager.persist(x);
        }

        for (int i = 0; i < 120; i++)
        {
            Ucenik x = new Ucenik("Ucenik ", UUID.randomUUID().toString().substring(0, 3));
            x.setKorisnickiNalog(new KorisnickiNalog("ucenik" + i, "test"));
            ucenici.add(x);
            entityManager.persist(x);
        }
        for (int i = 0; i < 48; i++)
        {
            Predmet x = new Predmet("Predmet " + UUID.randomUUID().toString().substring(0, 3), i % 4);
            predmeti.add(x);
            entityManager.persist(x);
        }

        for (int i = 1; i <= 4; i++)
        {
            Odjeljenje x1 = new Odjeljenje("2015/16", i, i + "-a", nastavniks.get(i % 20));
            Odjeljenje x2 = new Odjeljenje("2015/16", i, i + "-b", nastavniks.get(i % 20));

            odjeljenja.add(x1);
            odjeljenja.add(x2);

            entityManager.persist(x1);
            entityManager.persist(x2);

        }
        int b = 0;

        for (Ucenik x : ucenici)
        {
            Odjeljenje o = odjeljenja.get(b % odjeljenja.size());
            b++;
            OdjeljenjeStavka stavka = new OdjeljenjeStavka(x, o, 0);
            entityManager.persist(stavka);

            for (Predmet p : predmeti.stream().filter(w -> w.getRazred() == o.getRazred()).collect(Collectors.toList()))
            {
                DodjeljenPredmet dodjeljenPredmet = new DodjeljenPredmet(stavka, p, dajOcjenu(), dajOcjenu());
                entityManager.persist(dodjeljenPredmet);
            }
        }
    }
    private static int dajOcjenu()
    {
        Random r = new Random();
        int Low = 1;
        int High = 5;
        int Result = r.nextInt(High-Low) + Low;
        return Result;
    }


}