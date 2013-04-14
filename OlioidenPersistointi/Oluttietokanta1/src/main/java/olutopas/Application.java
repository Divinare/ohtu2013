package olutopas;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import java.util.Scanner;
import javax.persistence.OptimisticLockException;
import olutopas.model.Beer;
import olutopas.model.Brewery;
import olutopas.model.Pub;
import olutopas.model.Rating;
import olutopas.model.User;

public class Application {

    private EbeanServer server;
    private Scanner scanner = new Scanner(System.in);
    private User kayttaja;

    public Application(EbeanServer server) {
        this.server = server;
    }

    public void run(boolean newDatabase) {
        if (newDatabase) {
            seedDatabase();
        }

        System.out.println("Welcome!");

        while (true) {
            if (kayttaja == null) {
                login();
            }
            menu();
            System.out.print("> ");
            String command = scanner.nextLine();

            if (command.equals("0")) {
                break;
            } else if (command.equals("1")) {
                findBrewery();
            } else if (command.equals("2")) {
                findBeer();
            } else if (command.equals("3")) {
                addBeer();
            } else if (command.equals("4")) {
                listBreweries();
            } else if (command.equals("5")) {
                deleteBeer();
            } else if (command.equals("6")) {
                listBeers();
            } else if (command.equals("7")) {
                addBrewery();
            } else if (command.equals("8")) {
                deleteBrewery();
            } else if (command.equals("9")) {
                addPub();
            } else if (command.equals("10")) {
                addBeerToPub();
            } else if (command.equals("11")) {
                listPubs();
            } else if (command.equals("12")) {
                showBeerInPub();
            } else if (command.equals("y")) {
                listUsers();
            } else if (command.equals("13")) {
                removeBeerFromPub();
            } else if (command.equals("y")) {
                listUsers();
            } else if (command.equals("t")) {
                listRatings();
            } else {
                System.out.println("unknown command");
            }

            System.out.print("\npress enter to continue");
            scanner.nextLine();
        }

        System.out.println("bye");
    }

    private void menu() {
        System.out.println("");
        System.out.println("1   find brewery");
        System.out.println("2   find/rate beer");
        System.out.println("3   add beer");
        System.out.println("4   list breweries");
        System.out.println("5   delete beer");
        System.out.println("6   list beers");
        System.out.println("7   add brewery");
        System.out.println("9   add pub");
        System.out.println("10  add beer to pub");
        System.out.println("11  list pubs and beers");
        System.out.println("12  show beers in a pub");
        System.out.println("13  remove beer from a pub");
        System.out.println("...");
        System.out.println("t   show my ratings");
        System.out.println("y   list users");
        System.out.println("0   quit");
        System.out.println("");
    }

    private void listRatings() {
        List<User> users = server.find(User.class).findList();
        for (User user : users) {
            System.out.println(user);
        }
    }

    private void listUsers() {
        List<User> users = server.find(User.class).findList();
        for (User u : users) {
            System.out.println(u);
        }
    }

    private void listBeers() {
        List<Beer> beers = server.find(Beer.class).findList();
        for (Beer b : beers) {
            System.out.println(b);
        }
    }

    private void addBrewery() {
        System.out.print("brewery to add: ");
        String name = scanner.nextLine();
        Brewery exists = server.find(Brewery.class).where().like("name", name).findUnique();
        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }
        Brewery b = new Brewery(name);
        server.save(b);
        System.out.println(name + " added to " + b.getName());
    }

    private void deleteBrewery() {
        System.out.print("brewery to delete: ");
        String name = scanner.nextLine();
        Brewery b = server.find(Brewery.class).where().like("name", name).findUnique();
        if (b == null) {
            System.out.println(name + " doenst exist");
            return;
        }
        server.delete(b);
        System.out.println(name + " deleted");

    }

    // jos kanta on luotu uudelleen, suoritetaan tämä ja laitetaan kantaan hiukan dataa
    private void seedDatabase() throws OptimisticLockException {
        Brewery brewery = new Brewery("Schlenkerla");
        brewery.addBeer(new Beer("Urbock"));
        brewery.addBeer(new Beer("Lager"));
        // tallettaa myös luodut oluet, sillä Brewery:n OneToMany-mappingiin on määritelty
        // CascadeType.all
        server.save(brewery);

        // luodaan olut ilman panimon asettamista
        Beer b = new Beer("Märzen");
        server.save(b);

        // jotta saamme panimon asetettua, tulee olot lukea uudelleen kannasta
        b = server.find(Beer.class, b.getId());
        brewery = server.find(Brewery.class, brewery.getId());
        brewery.addBeer(b);
        server.save(brewery);

        server.save(new Brewery("Paulaner"));
    }

    private void findBeer() {
        System.out.print("beer to find: ");
        String n = scanner.nextLine();
        Beer foundBeer = server.find(Beer.class).where().like("name", n).findUnique();

        if (foundBeer == null) {
            System.out.println(n + " not available currently!");
            return;
        } else {
            System.out.println(foundBeer);
            findRatings(foundBeer);
            System.out.print("give rating (leave emtpy if not): ");
            String arvosana = scanner.nextLine();
            if (arvosana.equals("")) {
                return;
            } else {
                int rate = Integer.parseInt(arvosana);
                Rating arvostelu = new Rating(foundBeer, this.kayttaja, rate);
                server.save(arvostelu);
            }
        }

        System.out.println("found: " + foundBeer);
    }

    private void findRatings(Beer id) {
        List<Rating> lista = server.find(Rating.class).where().like("beer_id", id.getId().toString()).findList();
        double keskiarvo = 0;
        if (lista.isEmpty()) {
            System.out.println(" not available currently!");
        } else {
            for (Rating ratings : lista) {
                keskiarvo = keskiarvo + ratings.getValue();
            }
            keskiarvo = (keskiarvo / lista.size());
            System.out.println(" number of ratings: " + lista.size() + " average " + keskiarvo);
        }
    }

    private void findBrewery() {
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

    private void listBreweries() {
        List<Brewery> breweries = server.find(Brewery.class).findList();
        for (Brewery brewery : breweries) {
            System.out.println(brewery);
        }
    }

    private void addBeer() {
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

    private void deleteBeer() {
        System.out.print("beer to delete: ");
        String n = scanner.nextLine();
        Beer beerToDelete = server.find(Beer.class).where().like("name", n).findUnique();

        if (beerToDelete == null) {
            System.out.println(n + " not found");
            return;
        }

        server.delete(beerToDelete);
        System.out.println("deleted: " + beerToDelete);

    }

    private void login() {
        System.out.println("Login (give ? to register a new user)");
        System.out.print("username: ");
        String nimi = scanner.nextLine();
        if (nimi.equals("?")) {
            createNew();
        } else {
            User kayttaja = server.find(User.class).where().like("kayttajatunnus", nimi).findUnique();
            if (kayttaja != null) {
                System.out.println("");
                System.out.println("Welcome to Ratebeer " + nimi);
                this.kayttaja = kayttaja;
            } else {
                System.out.println("User " + nimi + " doesnt exist");
                // palataan alkuun
                login();
            }
        }
    }

    private void createNew() {
        System.out.println("Register a new user");
        System.out.print("give username: ");
        String nimi = scanner.nextLine();
        User user = server.find(User.class).where().like("kayttajatunnus", nimi).findUnique();
        if (user != null) {
            System.out.println("username already exist!");
            return;
        }
        User u = new User();
        u.setKayttajatunnus(nimi);
        server.save(u);
        System.out.println("user created!");
        login();
    }

    private void addPub() {
        System.out.print("give pub name to add: ");
        String name = scanner.nextLine();
        Pub exists = server.find(Pub.class).where().like("name", name).findUnique();
        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }
        server.save(new Pub(name));
    }

    private void addBeerToPub() {
        System.out.print("beer name: ");
        String name = scanner.nextLine();
        Beer beer = server.find(Beer.class).where().like("name", name).findUnique();
        if (beer == null) {
            System.out.println("does not exist");
            return;
        }
        System.out.print("pub name: ");
        name = scanner.nextLine();
        Pub pub = server.find(Pub.class).where().like("name", name).findUnique();

        if (pub == null) {
            System.out.println("does not exist");
            return;
        }
        pub.addBeer(beer);
        server.save(pub);
    }

    private void listPubs() {
        System.out.println("pub list:");
        List<Pub> pubilista = server.find(Pub.class).findList();
        for (Pub pub : pubilista) {
            System.out.println("beers in " + pub.getName() + ":");
            showBeerInPub(pub.getName());
        }
    }

    private void showBeerInPub(String name) {
        Pub olemassa = server.find(Pub.class).where().like("name", name).findUnique();
        if (olemassa == null) {
            System.out.println("there is no such pub");
            return;
        }

        List<Beer> oluet = olemassa.getBeers();
        for (Beer olut : oluet) {
            System.out.println(olut.getName());
        }
    }

    private void showBeerInPub() {
        System.out.println("pub to show: ");
        String name = scanner.nextLine();
        showBeerInPub(name);
    }

    private void removeBeerFromPub() {
        System.out.print("where to remove: ");
        String name = scanner.nextLine();
        Pub pub = server.find(Pub.class).where().like("name", name).findUnique();
        if (pub == null) {
            System.out.println("not found");
            return;
        }
        System.out.print("which beer to remove: ");
        name = scanner.nextLine();
        Beer beer = server.find(Beer.class).where().like("name", name).findUnique();
        pub.removeBeer(beer);
        server.save(pub);
    }
    
}
