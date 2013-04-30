/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.commands;

import olutopas.Datamapper;
import olutopas.model.Rating;
import olutopas.model.User;

public class ListRatings implements Command {

    private Datamapper datamapper;

    public ListRatings(Datamapper datamapper) {
        this.datamapper = datamapper;
    }

    @Override
    public void perform() {
        System.out.println("Ratings by " + datamapper.getUser().getName());
        for (Rating rating : datamapper.getUser().getRatings()) {
            System.out.println(rating);
        }
    }

    public String toString() {
        return "list my ratings";
    }
}
