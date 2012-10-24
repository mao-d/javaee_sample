package sample.museum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CollectedItem {

    @Id
    @Column(name = "museum_id", nullable = false)
    private Long id;

    @Column(name = "itemName", nullable = false)
    private String itemName;

    @JoinColumn(name = "museum_id", referencedColumnName="museum_id", nullable = false)
    @ManyToOne
    private Museum museum;

    public CollectedItem() {
    }

    public CollectedItem(Long id, String itemNames) {
        setId(id);
        setItemName(itemNames);
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

    public Museum getMuseum() {
        return museum;
    }

    public void setMuseum(Museum museum) {
        this.museum = museum;
    }
}
