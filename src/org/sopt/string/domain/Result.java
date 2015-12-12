package org.sopt.string.domain;

public class Result {

    public int start;
    public int end;

    public Result(int start, int end) {

        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() { return String.format("%d ~ %d", start, end); }
}
