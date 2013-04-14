package olutopas.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Rating {

    private int value;
    
    @Id
    private Integer id;
    
    @ManyToOne
    private Beer beer;

    @ManyToOne
    private User user;
    
    public Rating() {
        
    }
    
    public Rating(Beer olut, User kayttaja, int value){
        this.beer = olut;
        this.user = kayttaja;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Integer getId() {
        return id;
    }

    public Beer getBeer() {
        return beer;
    }

    public User getUser() {
        return user;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    public void setUser(User user) {
        this.user = user;
    }
    


}
