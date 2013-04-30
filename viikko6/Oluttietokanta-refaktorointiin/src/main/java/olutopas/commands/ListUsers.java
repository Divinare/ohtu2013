package olutopas.commands;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import olutopas.Datamapper;
import olutopas.model.User;

public class ListUsers implements Command {

    private EbeanServer server;

    public ListUsers(Datamapper datamapper) {
        this.server = datamapper.getServer();

    }

    @Override
    public void perform() {

        List<User> users = server.find(User.class).findList();
        for (User user : users) {
            System.out.println(user.getName() + " " + user.getRatings().size() + " ratings");

        }
    }

    @Override
    public String toString() {
        return "list users";
    }
}
