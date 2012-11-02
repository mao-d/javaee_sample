package sample.museum;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import sample.log.Logged;

@ManagedBean
@RequestScoped
public class MuseumController {

    @EJB
    private transient MuseumEJB museumEJB;

    @EJB
    private transient CollectedItemEJB collectedItemEJB;

    /** 検索結果. */
    private List<Museum> museumList = new ArrayList<Museum>();

    /** 検索キーワードの入力内容. */
    private Museum museum = new Museum();

    /** 検索キーワードの所蔵品の入力内容. */
    private CollectedItem collectedItem = new CollectedItem();

    @Logged
    public String doListMuseums() {
        if (museum == null || collectedItem == null) {
            return "system_error.xhtml";
        }
        if (EJBUtility.isNull(museum.getName()) &&
                EJBUtility.isNull(museum.getPlace()) &&
                EJBUtility.isNull(museum.getYear()) &&
                EJBUtility.isNull(collectedItem.getItem_name())) {
            return "operation_error.xhtml";
        }

        if (collectedItem.getItem_name() == null || collectedItem.getItem_name().equals("")) {
            setMuseumList(museumEJB.findMuseumByQuery(museum));
        } else {
            setMuseumList(collectedItemEJB.findMuseumsByItemName(collectedItem.getItem_name(), museum));
        }

        if (museumList.isEmpty()) {
            return "system_error.xhtml";
        }

        return "search.xhtml";
    }

    @Logged
    public String doReset() {
        museum = new Museum();
        collectedItem = new CollectedItem();
        return "search.xhtml";
    }

    @Logged
    public String doReturn() {
        return "search.xhtml";
    }

    //-------getter/setter-------
    public Museum getMuseum() {
        return museum;
    }

    public void setMuseum(Museum museum) {
        this.museum = museum;
    }

    public CollectedItem getCollectedItem() {
        return collectedItem;
    }

    public void setCollectedItem(CollectedItem collectedItem) {
        this.collectedItem = collectedItem;
    }

    public List<Museum> getMuseumList() {
        return museumList;
    }

    public void setMuseumList(List<Museum> museumList) {
        this.museumList = museumList;
    }
}
