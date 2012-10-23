package sample.museum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "findItemsById", query = "SELECT i FROM Museum i WHERE i.id = :id"),
    @NamedQuery(name = "findItemsByItemName", query = "SELECT i FROM Museum i WHERE i.name = :name")
})
public class CollectedItem {

    @Id
    private Long id;

    @Column(nullable = false)
    private String itemName;

    public CollectedItem() {
    }

    public CollectedItem(Long id, String itemName) {
        setId(id);
        setItemName(itemName);
    }

    //-------getter/setter-------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
