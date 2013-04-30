/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.commands;

import com.avaje.ebean.EbeanServer;
import java.util.Scanner;
import olutopas.Datamapper;
import olutopas.model.Brewery;

public class AddBrewery implements Command {

    private Scanner scanner = new Scanner(System.in);
    private EbeanServer server;

    public AddBrewery(Datamapper datamapper) {
        this.server = datamapper.getServer();
    }

    @Override
    public void perform() {

        System.out.print("brewery to add: ");
        String name = scanner.nextLine();
        Brewery brewery = server.find(Brewery.class).where().like("name", name).findUnique();

        if (brewery != null) {
            System.out.println(name + " already exists!");
            return;
        }

        server.save(new Brewery(name));

    }

    public String toString() {
        return "add brewery";
    }
}
