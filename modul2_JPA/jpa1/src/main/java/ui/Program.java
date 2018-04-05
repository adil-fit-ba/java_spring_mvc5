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


     try {
         em.getTransaction().begin();
         em.persist(predmet);
         em.getTransaction().commit();
     }catch (Exception e)
     {
         em.getTransaction().rollback();
     }
        em.close();

    }

    private static void ispis() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MojaOznaka1");

        EntityManager em = emf.createEntityManager();

        List<Object[]> podaci = em.createQuery("select x.id, x. ocjenaBrojcano, x.ocjenaOpis, x.datum, x.predmet.naziv from Ocjena x", Object[].class).getResultList();

        for (Object[] x : podaci) {

            System.out.println("id" + x[0]);
            System.out.println("ocjenaBrojcano" + x[1]);
            System.out.println("ocjenaOpis" + x[2]);
        }


        em.close();
    }


}
