package sample.museum;

import java.io.Serializable;

public class CollectedItemPK implements Serializable {
    private static final long serialVersionUID = -8462242485405179319L;
    private Long museum_id;
    private String item_name;

    public CollectedItemPK() {
    }

    public CollectedItemPK(Long museum_id, String item_name) {
        setMuseumId(museum_id);
        setItem_name(item_name);
    }

    public int hashCode() {
        return (museum_id == null ? 0 : museum_id.hashCode()) +
                (item_name == null ? 0 : item_name.hashCode());
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || !(object instanceof CollectedItemPK)) {
            return false;
        }

        CollectedItemPK itemPK = (CollectedItemPK) object;

        if (museum_id != 0L && museum_id != itemPK.getMuseumId()) {
            return false;
        }

        if (museum_id == 0L && itemPK.getMuseumId() != 0L) {
            return false;
        }

        if (item_name != null && ! item_name.equals(itemPK.getItem_name())) {
            return false;
        }

        if (item_name == null && itemPK.getItem_name() != null) {
            return false;
        }

        return true;
    }

    //-------getter/setter-------
    public Long getMuseumId() {
        return museum_id;
    }

    public void setMuseumId(Long museum_id) {
        this.museum_id = museum_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }
}
