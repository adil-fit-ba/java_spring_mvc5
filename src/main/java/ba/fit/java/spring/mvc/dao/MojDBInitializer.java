package ba.fit.java.spring.mvc.dao;

import ba.fit.java.spring.mvc.entitymodels.*;
import ba.fit.java.spring.mvc.helper.MyJpaDao;
import org.hibernate.id.GUIDGenerator;
import org.hibernate.id.UUIDGenerator;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

public class MojDBInitializer
{
    public static void Izvrsi(EntityManager _context)
    {
        // Look for any students.
        if (_context.createQuery("select count(x) from Ucenik x").getFirstResult() > 0)
        {
            return;   // DB has been seeded
        }

        ArrayList<Ucenik> ucenici = new ArrayList<Ucenik>();
        ArrayList<Predmet> predmeti = new ArrayList<Predmet>();
        ArrayList<Odjeljenje> odjeljenja = new ArrayList<Odjeljenje>();
        ArrayList<Nastavnik> nastavniks = new ArrayList<Nastavnik>();

        for (int i = 0; i < 20; i++)
        {
            nastavniks.add(new Nastavnik ("prof", "Razrednik " + UUID.randomUUID().toString().substring(0, 3)) );
        }

        for (int i = 0; i < 120; i++)
        {
            ucenici.add(new Ucenik ("Ucenik ", UUID.randomUUID().toString().substring(0, 3)) );
        }
        for (int i = 0; i < 48; i++)
        {
            predmeti.add(new Predmet ( "Predmet " +UUID.randomUUID().toString().substring(0, 3), i % 4 ));
        }

        for (int i = 1; i <= 4; i++)
        {
            odjeljenja.add(new Odjeljenje( "2015/16", i,i + "-a",  nastavniks.get(i % 20) ));
            odjeljenja.add(new Odjeljenje( "2015/16", i,i + "-b",  nastavniks.get(i % 20) ));
        }
        int b = 0;

        for (Ucenik x : ucenici)
        {
            x.setKorisnickiNalog(new KorisnickiNalog("ucenik" + b, "test"));
            _context.persist(x);

            Odjeljenje o = odjeljenja.get(b % odjeljenja.size());
            b++;
            OdjeljenjeStavka stavka = new OdjeljenjeStavka(x, o, 0);
            _context.persist(stavka);

            for (Predmet p : predmeti.stream().filter(w -> w.getRazred() == o.getRazred()).collect(Collectors.toList()))
            {
                DodjeljenPredmet dodjeljenPredmet = new DodjeljenPredmet(stavka, p, dajOcjenu(), dajOcjenu());
                _context.persist(dodjeljenPredmet);
            }

        }

        int j = 0;
        for (Nastavnik x : nastavniks)
        {
            x.setKorisnickiNalog(new KorisnickiNalog("nastavnik" + ++j,"test"));
            _context.persist(x);
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