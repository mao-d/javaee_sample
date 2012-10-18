package sample.museum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CollectedItems {

    @Id
    private Long id;

    @Column(nullable = false)
    private String itemName;

    public CollectedItems() {
    }

    public CollectedItems(Long id, String itemName) {
        this.id = id;
        this.itemName = itemName;
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
