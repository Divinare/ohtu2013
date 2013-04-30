package olutopas.commands;

import com.avaje.ebean.EbeanServer;
import java.util.Scanner;
import olutopas.Datamapper;
import olutopas.model.Beer;
import olutopas.model.Brewery;
import olutopas.model.User;

public class FindBrewery implements Command {

    private EbeanServer server;
    private Scanner scanner = new Scanner(System.in);

    public FindBrewery(Datamapper datamapper) {
        this.server = datamapper.getServer();
    }
    @Override
    public void perform() {
        System.out.print("brewery to find: ");
        String n = scanner.nextLine();
        Brewery foundBrewery = server.find(Brewery.class).where().like("name", n).findUnique();

        if (foundBrewery == null) {
            System.out.println(n + " not found");
            return;
        }

        System.out.println(foundBrewery);
        for (Beer bier : foundBrewery.getBeers()) {
            System.out.println("   " + bier.getName());
        }
    }

    @Override
    public String toString() {
        return "find brewery";
    }
}
