package org.sopt.string.domain;

import java.util.ArrayList;

public class ResultSet {

    private boolean found;

    public boolean found() { return found; }
    public void found(boolean found) { this.found = found; }

    private ArrayList<Result> results;

    public ArrayList<Result> results() { return results; }
    public void results(ArrayList<Result> results) { this.results = results; }
}
