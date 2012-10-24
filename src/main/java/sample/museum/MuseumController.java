package sample.museum;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
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

    private Museum museum = new Museum();

    // TODO CollectedItem‚à•K—v

    private List<Museum> museumList = new ArrayList<Museum>();

    @Logged
    /*
     * Museum‚ðŒŸõ‚·‚é.
     * AndŒŸõ‚ðs‚¤.
     */
    public String doListMuseums(Museum museum) {
        // TODO CollectedItem‚ªnull‚©”Û‚©
        /*if (museum.getId() == null && (museum.getName() == null || museum.getName().equals(""))
                && (museum.getPlace() == null || museum.getPlace().equals(""))
                && (museum.getYear() > 0 && museum.getItemList().isEmpty())) {
            return "operation_error.xhtml";

        }
        List<Museum> museums = new ArrayList<Museum>();*/
       /* museums = findMuseumsByName(museum.getName(), museums);
        museums = findMuseumsByPlace(museum.getPlace(), museums);
        museums = findMuseumsByYear(museum.getYear(), museums);*/

        //setMuseumList(museums);
        return "search.xhtml";
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
