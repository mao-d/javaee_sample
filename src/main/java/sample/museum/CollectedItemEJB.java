package sample.museum;

import java.util.ArrayList;
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
    public List<Museum> findMuseumsByItemName(String itemName, Museum museum) {
        List<CollectedItem> items = createQuery(itemName, museum).getResultList();
        List<Museum> museums = new ArrayList<Museum>();
        museums.add(items.get(0).getMuseum());
        return museums;
    }

    @Logged
    private TypedQuery<CollectedItem> createQuery(String itemName, Museum museum) {
        // TODO JoinÇÕÇ¢ÇÁÇ»Ç¢ÅH -> ìÆÇ´Ç™Ç®Ç©ÇµÇ©Ç¡ÇΩÇÁçƒåüì¢
        String queryStr = "SELECT " + EJBUtility.MUSEUM_ACCESS + " FROM CollectedItem "+ EJBUtility.ITEM_ACCESS +", Museum " + EJBUtility.MUSEUM_ACCESS + " WHERE";
        StringBuffer whereStr = new StringBuffer();
        whereStr = EJBUtility.createWhereString(EJBUtility.NAMES_KEYWORD, EJBUtility.MUSEUM_ACCESS, museum.getName(), whereStr);
        whereStr = EJBUtility.createWhereString(EJBUtility.PlACES_KEYWORD, EJBUtility.MUSEUM_ACCESS, museum.getPlace(), whereStr);
        whereStr = EJBUtility.createWhereString(EJBUtility.YEARS_KEYWORD, EJBUtility.MUSEUM_ACCESS, museum.getYear(), whereStr);
        whereStr = EJBUtility.createWhereString(EJBUtility.ITEM_KEYWORD, EJBUtility.ITEM_ACCESS, itemName, whereStr);

        TypedQuery<CollectedItem> query = entityManager.createQuery(queryStr + whereStr, CollectedItem.class);
        setParameter(EJBUtility.NAMES_KEYWORD, museum.getName(), query);
        setParameter(EJBUtility.PlACES_KEYWORD, museum.getPlace(), query);
        setParameter(EJBUtility.YEARS_KEYWORD, museum.getYear(), query);
        setParameter(EJBUtility.YEARS_KEYWORD, museum.getYear(), query);
        setParameter(EJBUtility.ITEM_KEYWORD, itemName, query);

        return query;
    }

    private void setParameter(String keyword, String value, TypedQuery<CollectedItem> query) {
        if (EJBUtility.isNull(value)) {
            return;
        }
        query.setParameter(keyword, value);
    }

}
