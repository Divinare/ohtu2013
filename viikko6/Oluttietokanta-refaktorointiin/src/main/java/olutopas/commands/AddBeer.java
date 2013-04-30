/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.commands;

import com.avaje.ebean.EbeanServer;
import java.util.Scanner;
import olutopas.Datamapper;
import olutopas.model.Beer;
import olutopas.model.Brewery;

public class AddBeer implements Command {

    public AddBeer(Datamapper datamapper) {
        this.server = datamapper.getServer();
    }
    private EbeanServer server;
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void perform() {
        System.out.print("to which brewery: ");
        String name = scanner.nextLine();
        Brewery brewery = server.find(Brewery.class).where().like("name", name).findUnique();

        if (brewery == null) {
            System.out.println(name + " does not exist");
            return;
        }

        System.out.print("beer to add: ");

        name = scanner.nextLine();

        Beer exists = server.find(Beer.class).where().like("name", name).findUnique();
        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }

        brewery.addBeer(new Beer(name));
        server.save(brewery);
        System.out.println(name + " added to " + brewery.getName());
    }

    public String toString() {
        return "add beer";
    }
}
