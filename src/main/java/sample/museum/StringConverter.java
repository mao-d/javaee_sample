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
    * �����i����", "�Ōq������������󂯎��ACollectedItem�̃��X�g�Ƃ��ĕԂ�.
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
     * CollectedItem�̃��X�g�̒����珊���i���𔲂��o���A","�q���ŕ�����Ƃ��ďo�͂���.
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
            // itemListStr����łȂ��ꍇ��", "��ǉ�����.
            if (itemListStr.length() > 0) {
                itemListStr = itemListStr.append(", ");
            }
            itemListStr = itemListStr.append(item.getItem_name());
        }
        return itemListStr.toString();
    }

}
