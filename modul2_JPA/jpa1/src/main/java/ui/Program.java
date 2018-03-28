package ui;

import models.Ocjena;
import models.Predmet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Program {

    public static void main (String... params)
    {
        System.out.println("hello");

        ispis();

        dodaj();
    }

    private static void dodaj() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MojaOznaka1");
        EntityManager em = emf.createEntityManager();



        Predmet predmet = new Predmet();
        predmet.setNaziv("PR3");


        em.getTransaction().begin();
        em.persist(predmet);
        em.getTransaction().commit();

        em.close();

    }

    private static void ispis() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MojaOznaka1");

        EntityManager em = emf.createEntityManager();

        List<Ocjena> podaci = em.createQuery("select x from Ocjena x", Ocjena.class).getResultList();

        for (Ocjena x : podaci) {

            System.out.println("getOcjenaBrojcano" + x.getOcjenaBrojcano());
            System.out.println("getOcjenaOpis" + x.getOcjenaOpis());
            System.out.println("getDatum" + x.getDatum());
        }


        em.close();
    }


}
