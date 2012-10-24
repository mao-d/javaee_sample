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

    static public String NAMES_KEYWORD = "name";
    static public String PlACES_KEYWORD = "place";
    static public String YEARS_KEYWORD = "year";
    static public String MUSEUM_ACCESS = " m.";
    static public String COLON_EQUAL = " = :";
    // TODO  m. “™‚à’Ç‰Á

    @Logged
    public Museum findMuseumById(Long id) {
        return entityManager.find(Museum.class, id);
    }

    @Logged
    public List<Museum> findMuseumByQuery(Museum museum) {
        return createQuery(museum).getResultList();
    }

    @Logged
    public TypedQuery<Museum> createQuery(Museum museum) {
        String queryStr = "SELECT m FROM Museum m WHERE";
        StringBuffer whereStr = new StringBuffer();
        whereStr = createWhereString(NAMES_KEYWORD, MUSEUM_ACCESS, museum.getName(), whereStr);
        whereStr = createWhereString(PlACES_KEYWORD, MUSEUM_ACCESS, museum.getPlace(), whereStr);
        whereStr = createWhereString(YEARS_KEYWORD, MUSEUM_ACCESS, museum.getYear(), whereStr);

        TypedQuery<Museum> query = entityManager.createQuery(queryStr + whereStr, Museum.class);
        setParameter(NAMES_KEYWORD, museum.getName(), query);
        setParameter(PlACES_KEYWORD, museum.getPlace(), query);
        setParameter(YEARS_KEYWORD, museum.getYear(), query);

        return query;
    }

    public StringBuffer createWhereString(String keyword, String museumAccess, String value, StringBuffer whereStr) {
        if (isNull(value)) {
            return whereStr;
        }

        if (whereStr.length() > 0) {
            whereStr = whereStr.append(" AND ");
        }

        return whereStr.append(museumAccess + keyword + COLON_EQUAL + keyword);
    }

    private void setParameter(String keyword, String value, TypedQuery<Museum> query) {
        if (isNull(value)) {
            return;
        }
        query.setParameter(keyword, value);
    }

    public boolean isNull(String value) {
        return value == null || value.equals("");
    }
}
