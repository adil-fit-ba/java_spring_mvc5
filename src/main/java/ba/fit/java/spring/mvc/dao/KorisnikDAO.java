package ba.fit.java.spring.mvc.dao;

import ba.fit.java.spring.mvc.entitymodels.KorisnickiNalog;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional(readOnly = false)
public class KorisnikDAO {
    @PersistenceContext
    EntityManager entityManager;

    public void saveUserDetail(KorisnickiNalog user){
        entityManager.persist(user);
        System.out.println("--Data Saved--");
    }
}
