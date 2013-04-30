/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

import statistics.Player;

public class QueryBuilder {

    private Matcher matcher;

    public QueryBuilder() {
    }

    public QueryBuilder(Matcher m) {
        this.matcher = m;
    }

    public QueryBuilder playsIn(String team) {
        if (matcher == null) {
            matcher = new PlaysIn(team);
        }
        return new QueryBuilder(new And(matcher, new PlaysIn(team)));
    }

    public QueryBuilder hasAtLeast(int value, String category) {
        if (matcher == null) {
            matcher = new HasAtLeast(value, category);
        }
        return new QueryBuilder(new And(matcher, new HasAtLeast(value, category)));
    }

    public QueryBuilder hasFewerThan(int value, String category) {
        if (matcher == null) {
            matcher = new HasAtLeast(value, category);
        }
        return new QueryBuilder(new And(matcher, new HasAtLeast(value, category)));
    }

    public Matcher build() {
        return matcher;
    }

    public QueryBuilder oneOf(Matcher m1, Matcher m2) {
        return new QueryBuilder(new Or(m1, m2));
    }
}
