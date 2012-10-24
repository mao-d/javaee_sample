package sample.museum;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
@LocalBean
public class CollectedItemEJB {

    @PersistenceContext(unitName = "javaee6_sample")
    private EntityManager entityManager;

    @EJB
    private MuseumEJB museumEJB;

    static private String ITEM_KEYWORD = "item";
    static private String ITEM_ACCESS = " i.";

    public List<Museum> findMuseumsByItemName(String itemName, Museum museum) {
        List<CollectedItem> items = createQuery(itemName, museum).getResultList();
        List<Museum> museums = new ArrayList<Museum>();
        museums.add(items.get(0).getMuseum());
        return museums;
    }

    public TypedQuery<CollectedItem> createQuery(String itemName, Museum museum) {
        // TODO JoinÇÕÇ¢ÇÁÇ»Ç¢ÅH
        String queryStr = "SELECT m FROM CollectedItem i, Museum m WHERE";
        StringBuffer whereStr = new StringBuffer();
        whereStr = museumEJB.createWhereString(MuseumEJB.NAMES_KEYWORD, MuseumEJB.MUSEUM_ACCESS, museum.getName(), whereStr);
        whereStr = museumEJB.createWhereString(MuseumEJB.PlACES_KEYWORD, MuseumEJB.MUSEUM_ACCESS, museum.getPlace(), whereStr);
        whereStr = museumEJB.createWhereString(MuseumEJB.YEARS_KEYWORD, MuseumEJB.MUSEUM_ACCESS, museum.getYear(), whereStr);
        whereStr = museumEJB.createWhereString(ITEM_KEYWORD, ITEM_ACCESS, itemName, whereStr);
        TypedQuery<CollectedItem> query = entityManager.createQuery(queryStr + whereStr, CollectedItem.class);
        setParameter(MuseumEJB.NAMES_KEYWORD, museum.getName(), query);
        setParameter(MuseumEJB.PlACES_KEYWORD, museum.getPlace(), query);
        setParameter(MuseumEJB.YEARS_KEYWORD, museum.getYear(), query);
        setParameter(MuseumEJB.YEARS_KEYWORD, museum.getYear(), query);
        setParameter(ITEM_KEYWORD, itemName, query);

        return query;
    }

    private void setParameter(String keyword, String value, TypedQuery<CollectedItem> query) {
        if (museumEJB.isNull(value)) {
            return;
        }
        query.setParameter(keyword, value);
    }

}
