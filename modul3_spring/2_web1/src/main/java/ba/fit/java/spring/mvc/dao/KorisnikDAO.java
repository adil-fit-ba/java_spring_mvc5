package ba.fit.java.spring.mvc.dao;

import ba.fit.java.spring.mvc.entitymodels.KorisnickiNalog;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional(readOnly = true)
public class KorisnikDAO {
    @PersistenceContext
    EntityManager entityManager;
    @Transactional
    public void saveUserDetail(KorisnickiNalog user){
        entityManager.persist(user);
        System.out.println("--Data Saved--");
    }
}
