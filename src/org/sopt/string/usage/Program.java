package org.sopt.string.usage;

import org.sopt.string.domain.ResultSet;
import org.sopt.string.strategy.impl.RK;
import org.sopt.string.util.Matcher;

public class Program {

    public static void main(String[] args) {

        Matcher matcher = new Matcher();

        ResultSet result = matcher.find("Hello, world !! It is sample for testing string matching algorithm.", "in", new RK());

        if (!result.found()) {

            System.out.println("Cannot find :(");
            return;
        }

        result.results().forEach(System.out::println);
    }
}
