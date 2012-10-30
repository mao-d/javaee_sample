package sample.museum;

public class EJBUtility {
    static final public String NAMES_KEYWORD = "name";
    static final public String PlACES_KEYWORD = "place";
    static final public String YEARS_KEYWORD = "year";
    static final public String MUSEUM_ACCESS = " m";
    static final public String ITEM_KEYWORD = "item";
    static final public String ITEM_ACCESS = " i";


    public static StringBuffer createWhereString(String keyword, String museumAccess, String value, StringBuffer whereStr) {
        if (isNull(value)) {
            return whereStr;
        }

        if (whereStr.length() > 0) {
            whereStr = whereStr.append(" AND ");
        }
        return whereStr.append(museumAccess + "." + keyword + " = :" + keyword);
    }

    public static boolean isNull(String value) {
        return value == null || value.equals("");
    }
}
