package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class PersonManager {
    private EntityManager em;

    public PersonManager(EntityManagerFactory emf) {
        this.em = emf.createEntityManager();
    }

    public void addPerson(Person person) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(person);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public List<Person> getAllPersons() {
        return em.createQuery("from Person", Person.class).getResultList();
    }

    public void close() {
        em.close();
    }
}