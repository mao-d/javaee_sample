package sample.museum;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import sample.log.Logged;

@RequestScoped
@Named("search")
public class MuseumController {

    @EJB
    private transient MuseumEJB museumEJB;

    @EJB
    private transient CollectedItemEJB collectedItemEJB;

    /** 検索結果. */
    private List<Museum> museumList = new ArrayList<Museum>();

    /** 検索キーワードの入力内容. */
    private Museum museum;

    /** 検索キーワードの所蔵品の入力内容. */
    private CollectedItem collectedItem;

    @Logged
    public String doListMuseums() {
        if (museum == null) {
            return "operation_error.xhtml";
        }
        if (EJBUtility.isNull(museum.getName()) || EJBUtility.isNull(museum.getPlace()) || EJBUtility.isNull(museum.getYear())) {
            return "operation_error.xhtml";
        }

        if (collectedItem == null) {
            setMuseumList(museumEJB.findMuseumByQuery(museum));
        } else {
            setMuseumList(collectedItemEJB.findMuseumsByItemName(collectedItem.getItemName(), museum));
        }

        if (museumList.isEmpty()) {
            return "system_error.xhtml";
        }

        return "search.xhtml";
    }

    //-------getter/setter-------
    public List<Museum> getMuseumList() {
        return museumList;
    }

    public void setMuseumList(List<Museum> museumList) {
        this.museumList = museumList;
    }
}
