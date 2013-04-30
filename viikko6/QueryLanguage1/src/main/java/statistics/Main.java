package statistics;

import statistics.matcher.*;

public class Main {

    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstatistics.herokuapp.com/players.txt"));

//        Matcher m = new And(new HasAtLeast(10, "goals"),
//                new HasAtLeast(10, "assists"),
//                new PlaysIn("PHI"));
//
//        for (Player player : stats.matches(m)) {
//            System.out.println(player);
//        }

//        Matcher m = new Or(new HasFewerThan(20, "goals"),
//                new HasAtLeast(20, "assists"),
//                new PlaysIn("PHI"));
//
//        for (Player player : stats.matches(m)) {
//            System.out.println(player);
//        }

        QueryBuilder query = new QueryBuilder();

        Matcher m = query.hasAtLeast(10, "goals")
                .playsIn("NYR")
                .hasFewerThan(10, "assists").build();

//        QueryBuilder query = new QueryBuilder();
//        
//        Matcher m1 = query.playsIn("PHI")
//                .hasAtLeast(10, "goals")
//                .hasFewerThan(15, "assists").build();
//
//        Matcher m2 = query.playsIn("NYR")
//                .hasAtLeast(15, "points").build();
//
//        Matcher m = query.oneOf(m1, m2).build();



        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }
    }
}
