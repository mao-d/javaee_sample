package sample.museum;

public class EJBUtility {
    static public enum QueryType {
        EQUALS, LIKE;
    }
    static final public String NAMES_KEYWORD = "name";
    static final public String PlACES_KEYWORD = "place";
    static final public String YEARS_KEYWORD = "year";
    static final public String MUSEUM_ACCESS = " m";
    static final public String ITEM_KEYWORD = "item_name";
    static final public String ITEM_ACCESS = " i";


    public static StringBuffer createWhereString(String keyword, String museumAccess, String value, StringBuffer whereStr, QueryType type) {
        if (isNull(value)) {
            return whereStr;
        }

        if (whereStr.length() > 0) {
            whereStr = whereStr.append(" AND ");
        }

        if (type == QueryType.EQUALS) {
            return whereStr.append(museumAccess + "." + keyword + " = :" + keyword);
        } else {

        }
        return whereStr.append(museumAccess + "." + keyword + " LIKE :" + keyword);
    }

    public static boolean isNull(String value) {
        return value == null || value.equals("");
    }

}
