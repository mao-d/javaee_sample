package sample.museum;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import sample.log.Logged;

@Stateless
@LocalBean
public class MuseumEJB {

    @PersistenceContext(unitName = "javaee6_sample")
    private EntityManager entityManager;

    @Logged
    public Museum findMuseumById(Long id) {
        return entityManager.find(Museum.class, id);
    }

    @Logged
    public List<Museum> findMuseumByName(String name) {
        TypedQuery<Museum> query = entityManager.createNamedQuery("findMuseumByName", Museum.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    @Logged
    public List<Museum> findMuseumByPlace(String place) {
        TypedQuery<Museum> query = entityManager.createNamedQuery("findMuseumByPlace", Museum.class);
        query.setParameter("place", place);
        return query.getResultList();
    }

    @Logged
    public List<Museum> findMuseumByYear(Integer year) {
        TypedQuery<Museum> query = entityManager.createNamedQuery("findMuseumByYear", Museum.class);
        query.setParameter("place", year);
        return query.getResultList();
    }
}
