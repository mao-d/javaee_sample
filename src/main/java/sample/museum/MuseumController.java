package sample.museum;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import sample.log.Logged;

@ManagedBean
@RequestScoped
@Named("search")
public class MuseumController {

    @EJB
    private transient MuseumEJB museumEJB;

    @EJB
    private transient CollectedItemEJB collectedItemEJB;

    private Museum museum = new Museum();

    private List<Museum> museumList = new ArrayList<Museum>();

    @Logged
    /*
     * Museum‚ğŒŸõ‚·‚é.
     * AndŒŸõ‚ğs‚¤.
     */
    public String doListMuseums(Museum museum) {
        if (museum.getId() == null && (museum.getName() == null || museum.getName().equals(""))
                && (museum.getPlace() == null || museum.getPlace().equals(""))
                && (museum.getYear() == null && museum.getItemList().isEmpty())) {
            return "operation_error.xhtml";

        }
        List<Museum> museums = new ArrayList<Museum>();
        museums = findMuseumsByName(museum.getName(), museums);
        museums = findMuseumsByPlace(museum.getPlace(), museums);
        museums = findMuseumsByYear(museum.getYear(), museums);

        setMuseumList(museums);
        return "search.xhtml";
    }

    private List<Museum> findMuseumsByName(String name, List<Museum> museums) {
        List<Museum> newMuseumList = new ArrayList<Museum>(museums);
        if (name == null || name.equals("")) {
            return newMuseumList;
        }
        for (Museum museum : setItemsToMuseum(museumEJB.findMuseumByName(name))) {
            if (newMuseumList.contains(museum)) {
                continue;
            }
            newMuseumList.add(museum);
        }
        return newMuseumList;
    }

    private List<Museum> findMuseumsByPlace(String place, List<Museum> museums) {
        List<Museum> newMuseumList = new ArrayList<Museum>(museums);
        if (place == null || place.equals("")) {
            return newMuseumList;
        }
        for (Museum museum : setItemsToMuseum(museumEJB.findMuseumByPlace(place))) {
            if (newMuseumList.contains(museum)) {
                continue;
            }
            newMuseumList.add(museum);
        }
        return newMuseumList;
    }

    private List<Museum> findMuseumsByYear(Integer year, List<Museum> museums) {
        List<Museum> newMuseumList = new ArrayList<Museum>(museums);
        if (year == null) {
            return newMuseumList;
        }
        for (Museum museum : setItemsToMuseum(museumEJB.findMuseumByYear(year))) {
            if (newMuseumList.contains(museum)) {
                continue;
            }
            newMuseumList.add(museum);
        }
        return newMuseumList;
    }

    /*private List<Museum> findMuseumsByItems(List<CollectedItem> items, List<Museum> museums) {
        if () {

        }
    }*/

    private List<Museum> setItemsToMuseum(List<Museum> museums) {
        List<Museum> newMuseumList = new ArrayList<Museum>(museums);
        for (Museum museum : newMuseumList) {
            List<CollectedItem> items = collectedItemEJB.findItemsById(museum.getId());
            museum.setItemList(items);
        }
        return newMuseumList;
    }


    //-------getter/setter-------
    public Museum getMuseum() {
        return museum;
    }

    public void setMuseum(Museum museum) {
        this.museum = museum;
    }

    public List<Museum> getMuseumList() {
        return museumList;
    }

    public void setMuseumList(List<Museum> museumList) {
        this.museumList = museumList;
    }
}
