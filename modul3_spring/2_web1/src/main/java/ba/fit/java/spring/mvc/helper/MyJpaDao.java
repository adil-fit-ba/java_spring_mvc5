package ba.fit.java.spring.mvc.helper;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class MyJpaDao< T extends Serializable> {
    private Class<T> clazz;

    public MyJpaDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        clazz = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected EntityManager entityManager;



    public T findOne(Long id) {
        return entityManager.find(clazz, id);
    }

    public List<T> findAll() {
        return entityManager.createQuery("select x from " + clazz.getName() + " x").getResultList();
    }

    public void save(T entity) {
        entityManager.persist(entity);
    }

    public void update(T entity) {
        entityManager.merge(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }

    public void deleteById(Long entityId) {
        T entity = findOne(entityId);
        delete(entity);
    }

    public boolean any() {
        return entityManager.createQuery("select count(x) from " + clazz.getName() + " x").getFirstResult() > 0;
    }
}