package olutopas.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class User {

    private String kayttajatunnus;
    @Id
    private Integer id;

    public User() {
    }

    public void setKayttajatunnus(String kayttajatunnus) {
        this.kayttajatunnus = kayttajatunnus;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKayttajatunnus() {
        return kayttajatunnus;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" + getKayttajatunnus() + '}';
    }
}