package org.sopt.string.util;

import org.sopt.string.domain.ResultSet;
import org.sopt.string.strategy.base.Strategy;

public class Matcher {

    public ResultSet find(String from, String target, Strategy strategy) {

        return strategy.find(from, target);
    }
}
