package sample.museum;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@IdClass(CollectedItemPK.class)
@Entity
@Named("collectedItem")
@RequestScoped
public class CollectedItem {

    @Id
    @Column(name = "museum_id", nullable = false)
    private Long museum_id;

    @Id
    @Column(name = "item_name", nullable = false)
    @Size(max = 30)
    private String item_name;

    @JoinColumn(name = "museum_id", referencedColumnName="museum_id", nullable = false,
            insertable = false, updatable = false)
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
        return museum_id;
    }

    public void setId(Long id) {
        this.museum_id = id;
    }

    public String getItemName() {
        return item_name;
    }

    public void setItemName(String itemName) {
        this.item_name = itemName;
    }

    public Museum getMuseum() {
        return museum;
    }

    public void setMuseum(Museum museum) {
        this.museum = museum;
    }
}
