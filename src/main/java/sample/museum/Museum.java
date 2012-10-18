package sample.museum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "findMuseumByName", query = "SELECT m FROM Museum m WHERE m.name LIKE :name"),
    @NamedQuery(name = "findMuseumByPlace", query = "SELECT m FROM Museum m WHERE m.place = :place"),
    @NamedQuery(name = "findMuseumByYear", query = "SELECT m FROM Museum m WHERE m.year = :year")
})
public class Museum {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String place;

    @Column(length = 4)
    private int year;

    public Museum() {
    }

    public Museum(String name, String place, int year) {
        setName(name);
        setPlace(place);
        setYear(year);
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
