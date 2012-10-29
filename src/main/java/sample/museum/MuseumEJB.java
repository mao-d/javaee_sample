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
    public List<Museum> findMuseumByQuery(Museum museum) {
        return createQuery(museum).getResultList();
    }

    @Logged
    private TypedQuery<Museum> createQuery(Museum museum) {
        // TODO ÉRÉÅÉìÉg
        String queryStr = "SELECT "+ EJBUtility.MUSEUM_ACCESS +" FROM Museum "+ EJBUtility.MUSEUM_ACCESS +" WHERE";
        StringBuffer whereStr = new StringBuffer();
        whereStr = EJBUtility.createWhereString(EJBUtility.NAMES_KEYWORD, EJBUtility.MUSEUM_ACCESS, museum.getName(), whereStr);
        whereStr = EJBUtility.createWhereString(EJBUtility.PlACES_KEYWORD, EJBUtility.MUSEUM_ACCESS, museum.getPlace(), whereStr);
        whereStr = EJBUtility.createWhereString(EJBUtility.YEARS_KEYWORD, EJBUtility.MUSEUM_ACCESS, museum.getYear(), whereStr);

        TypedQuery<Museum> query = entityManager.createQuery(queryStr + whereStr, Museum.class);
        setParameter(EJBUtility.NAMES_KEYWORD, museum.getName(), query);
        setParameter(EJBUtility.PlACES_KEYWORD, museum.getPlace(), query);
        setParameter(EJBUtility.YEARS_KEYWORD, museum.getYear(), query);

        return query;
    }



    private void setParameter(String keyword, String value, TypedQuery<Museum> query) {
        if (EJBUtility.isNull(value)) {
            return;
        }
        query.setParameter(keyword, value);
    }

}
