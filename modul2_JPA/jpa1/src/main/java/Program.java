import dao.MojDBInitializer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Program {
    public static void main(String... params)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MojaOznaka1");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        MojDBInitializer.Izvrsi(em);
        em.getTransaction().commit();

        System.out.println("ok");
    }
}
