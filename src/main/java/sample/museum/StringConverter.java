package sample.museum;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "stringConverter", forClass = List.class)
public class StringConverter implements Converter {

   /**
    * 所蔵品名を", "で繋げた文字列を受け取り、CollectedItemのリストとして返す.
    */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        String[] itemArray = value.split(", ");
        List<CollectedItem> collectedItems = new ArrayList<CollectedItem>();
        for (String item: itemArray) {
            CollectedItem collectedItem = new CollectedItem();
            collectedItem.setItem_name(item);
            collectedItems.add(collectedItem);
        }
        return collectedItems;
    }

    /**
     * CollectedItemのリストの中から所蔵品名を抜き出し、","繋ぎで文字列として出力する.
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        List<CollectedItem> valueList = (List) value;
        if (valueList.isEmpty()) {
            return "";
        }

        StringBuffer itemListStr = new StringBuffer();
        for (CollectedItem item: valueList) {
            // itemListStrが空でない場合は", "を追加する.
            if (itemListStr.length() > 0) {
                itemListStr = itemListStr.append(", ");
            }
            itemListStr = itemListStr.append(item.getItem_name());
        }
        return itemListStr.toString();
    }

}
