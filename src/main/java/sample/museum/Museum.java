package sample.museum;

import java.util.List;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "findMuseumByName", query = "SELECT m FROM Museum m WHERE m.name LIKE :name"),
    @NamedQuery(name = "findMuseumByPlace", query = "SELECT m FROM Museum m WHERE m.place = :place"),
    @NamedQuery(name = "findMuseumByYear", query = "SELECT m FROM Museum m WHERE m.year = :year")
})
@Named("museum")
public class Museum {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String place;

    @Column(nullable = false, length = 4)
    private Integer year;

    private List<CollectedItem> itemList;

    public Museum() {
    }

    public Museum(String name, String place, Integer year, List<CollectedItem> itemList) {
        setName(name);
        setPlace(place);
        setYear(year);
        setItemList(itemList);
    }

    public String getItemListString() {
        if (itemList.isEmpty()) {
            return "";
        }

        String itemListStr = "";
        for (CollectedItem item: itemList) {
            itemListStr = itemListStr.concat(item.getItemName() + " ");
        }

        return itemListStr;
    }

    //-------getter/setter-------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String Place) {
        this.place = Place;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<CollectedItem> getItemList() {
        return itemList;
    }

    public void addItemList(CollectedItem item) {
        itemList.add(item);
    }

    public void setItemList(List<CollectedItem> itemList) {
        this.itemList = itemList;
    }
}
