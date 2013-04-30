/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.commands;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import olutopas.Datamapper;
import olutopas.model.Brewery;

public class ListBreweries implements Command {
    
    private EbeanServer server;

    public ListBreweries(Datamapper datamapper) {
        this.server = datamapper.getServer();
    }
    
    @Override
    public void perform() {
        List<Brewery> breweries = server.find(Brewery.class).findList();
        for (Brewery brewery : breweries) {
            System.out.println(brewery);
        }
    }
    
    @Override
    public String toString() {
        return "list breweries";
    }
}
