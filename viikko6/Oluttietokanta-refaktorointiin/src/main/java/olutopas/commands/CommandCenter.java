/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.commands;

import java.util.ArrayList;
import java.util.List;
import olutopas.Datamapper;

public class CommandCenter {

    private ArrayList<Command> commands;

    public CommandCenter(Datamapper dataMapper) {
        commands = new ArrayList<Command>();
        commands.add(new AddBeer(dataMapper));
        commands.add(new AddBrewery(dataMapper));
        commands.add(new FindBeer(dataMapper));
        commands.add(new FindBrewery(dataMapper));
        commands.add(new ListBeers(dataMapper));
        commands.add(new ListBreweries(dataMapper));
        commands.add(new ListRatings(dataMapper));
        commands.add(new ListUsers(dataMapper));
    }

    public List getCommands() {
        return commands;
    }
}