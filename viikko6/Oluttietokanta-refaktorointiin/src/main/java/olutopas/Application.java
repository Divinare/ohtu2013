package olutopas;

import com.avaje.ebean.EbeanServer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.OptimisticLockException;
import olutopas.model.Beer;
import olutopas.model.Brewery;
import olutopas.model.Rating;
import olutopas.model.User;
import olutopas.commands.Command;
import olutopas.commands.CommandCenter;

public class Application {

    private Scanner scanner = new Scanner(System.in);
    private List<Command> commands;
    private Datamapper datamapper;

    public Application(Datamapper datamapper) {
        this.datamapper = datamapper;
        this.commands = new CommandCenter(datamapper).getCommands();
    }

    public void run(boolean newDatabase) {
        if (newDatabase) {
            seedDatabase();
        }

        login();

        System.out.println("\nWelcome to Ratebeer " + datamapper.getUser().getName());

        while (true) {
            menu();
            System.out.print("> ");
            String command = scanner.nextLine();

            if (command.equals("q")) {
                break;
            }
//            try {
//                int komento = Integer.parseInt(command);
//                commands.get(komento).perform();
//            } catch (Exception e) {
//                System.out.println("unknown command");
//            }
            try {
                int komento = Integer.parseInt(command);
                Command k = commands.get(komento);
                try {
                    k.perform();
                } catch (Exception e) {
                    System.out.println("failed to execute command");
                    System.out.println(e.toString());
                }
            } catch (Exception e) {
                System.out.println("unknown command");
            }

            System.out.print("\npress enter to continue");
            scanner.nextLine();
        }

        System.out.println("bye");
    }

    private void menu() {
        for (int i = 0; i < commands.size(); i++) {
            System.out.println(i + "  " + commands.get(i).toString());
        }
        System.out.println("q   quit");
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void seedDatabase() throws OptimisticLockException {
        Brewery brewery = new Brewery("Schlenkerla");
        brewery.addBeer(new Beer("Urbock"));
        brewery.addBeer(new Beer("Lager"));
        // tallettaa myös luodut oluet, sillä Brewery:n OneToMany-mappingiin on määritelty
        // CascadeType.all
        datamapper.save(brewery);

        // luodaan olut ilman panimon asettamista
        Beer b = new Beer("Märzen");
        datamapper.save(b);

        // jotta saamme panimon asetettua, tulee olot lukea uudelleen kannasta
        b = (Beer) datamapper.findID(Beer.class, b.getId());
        brewery = (Brewery) datamapper.findID(Brewery.class, brewery.getId());
        brewery.addBeer(b);
        datamapper.save(brewery);

        datamapper.save(new Brewery("Paulaner"));

        datamapper.save(new User("mluukkai"));
    }

    public void login() {
        while (true) {
            System.out.println("\nLogin (give ? to register a new user)\n");

            System.out.print("username: ");
            String name = scanner.nextLine();

            if (name.equals("?")) {
                registerUser();
                continue;
            }

//            datamapper.setUser(datamapper.getServer().find(User.class).where().like("name", name).findUnique());
            datamapper.setUser((User) datamapper.findName(User.class, name));
            if (datamapper.getUser() != null) {
                break;
            }
            System.out.println("unknown user");
        }
    }

    private void registerUser() {
        System.out.println("Register a new user");
        System.out.print("give username: ");
        String name = scanner.nextLine();
//        User u = (User)datamapper.findName(User.class).where().like("name", name).findUnique();
        User u = (User) datamapper.findName(User.class, name);

        if (u != null) {
            System.out.println("user already exists!");
            return;
        }
        datamapper.save(new User(name));
        System.out.println("user created!\n");
    }
}
