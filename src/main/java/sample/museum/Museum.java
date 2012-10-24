package sample.museum;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Museum {

    @Id @GeneratedValue
    @Column(name = "museum_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "place", nullable = false)
    private String place;

    @Column(name = "year", nullable = false, length = 4)
    private String year;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "museum")
    private List<CollectedItem> itemList;

    public Museum() {
    }

    public Museum(String name, String place, String year, List<CollectedItem> itemList) {
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
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
