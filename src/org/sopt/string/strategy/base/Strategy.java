package org.sopt.string.strategy.base;

import org.sopt.string.domain.ResultSet;

public interface Strategy {

    ResultSet find(String from, String target);
}
