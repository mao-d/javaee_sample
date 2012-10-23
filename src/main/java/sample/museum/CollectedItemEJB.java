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
public class CollectedItemEJB {

    @PersistenceContext(unitName = "javaee6_sample")
    private EntityManager entityManager;

    @Logged
    public List<CollectedItem> findItemsById(Long id) {
        TypedQuery<CollectedItem> query = entityManager.createNamedQuery("findItemsById", CollectedItem.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<CollectedItem> findItemsByItemName(String name) {
        TypedQuery<CollectedItem> query = entityManager.createNamedQuery("findItemsByItemName", CollectedItem.class);
        query.setParameter("name", name);
        return query.getResultList();
    }
}
