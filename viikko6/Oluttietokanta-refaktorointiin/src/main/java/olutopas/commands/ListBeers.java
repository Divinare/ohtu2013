/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package olutopas.commands;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import olutopas.Datamapper;
import olutopas.model.Beer;

public class ListBeers implements Command {

    private EbeanServer server;

    public ListBeers(Datamapper datamapper) {
        this.server = datamapper.getServer();
    }
    
    @Override
    public void perform() {

        List<Beer> beers = server.find(Beer.class).orderBy("brewery.name").findList();
        for (Beer beer : beers) {
            System.out.println(beer);
            if (beer.getRatings() != null && beer.getRatings().size() != 0) {
                System.out.println("  ratings given " + beer.getRatings().size() + " average " + beer.averageRating());
            } else {
                System.out.println("  no ratings");
            }
        }
    
    }
    @Override
    public String toString() {
        return "list beers";
    }

} 