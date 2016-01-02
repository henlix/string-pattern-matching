package org.sopt.string.strategy.impl;

import org.sopt.string.domain.Result;
import org.sopt.string.domain.ResultSet;
import org.sopt.string.strategy.base.Strategy;

public class BM implements Strategy {


    private static final int ASCII_NUM = 127;

    @Override
    public ResultSet find(String from, String target) {

        ResultSet set = new ResultSet();

        int bmBc[] = new int[ASCII_NUM];
        int bmGs[] = new int[ASCII_NUM];


        preBmBc(target, target.length(), bmBc);
        preBmGs(target, target.length(), bmGs);

        int j = 0;

        while (j <= from.length() - target.length()) {

            int i;
            for (i = target.length() - 1; i >= 0 && from.charAt(i + j) == target.charAt(i); i--) ;

            if (i < 0) {

                set.add(new Result(j, j + target.length() - 1));
                j += bmGs[0];

            } else {
                j += bmGs[i] > bmBc[from.charAt(i + j)] - target.length() + 1 + i ?
                        bmGs[i] : bmBc[from.charAt(i + j)] - target.length() + 1 + i;
            }
        }
        return set;
    }

    public void preBmBc(String target, int targetLength, int[] bmBc) {

        for (int i = 0; i < ASCII_NUM; i++)
            bmBc[i] = targetLength;

        for (int i = 0; i < targetLength - 1; i++)
            bmBc[target.charAt(i)] = targetLength - i - 1;
    }

    private void suffixes(String target, int targetLength, int[] suff) {

        int f = 0;
        int g = targetLength - 1;


        for (int i = targetLength - 2; i >= 0; i--) {
            if (i > g && suff[i + targetLength - 1 - f] < i - g)
                suff[i] = suff[i + targetLength - 1 - f];
            else {
                if (i < g)
                    g = i;
                f = i;
                while (g >= 0 && target.charAt(g) == target.charAt(g + targetLength - 1 - f)) {
                    --g;
                }
                suff[i] = f - g;
            }
        }
        suff[targetLength - 1] = targetLength;

    }




    private void preBmGs(String target, int targetLength, int[] bmGs) {

        int i, j = 0;

        int[] suff = new int[targetLength];

        suffixes(target, targetLength, suff);

        for (i = 0; i < targetLength; ++i)
            bmGs[i] = targetLength;

        for (i = targetLength - 1; i >= 0; --i) {
            if (suff[i] == i + 1) {
                for (; j < targetLength - 1 - i; ++j) {
                    if (bmGs[j] == targetLength)
                        bmGs[j] = targetLength - 1 - i;
                }
            }

        }

        for (i = 0; i <= targetLength - 2; ++i)
        {
            bmGs[targetLength - 1 - suff[i]] = targetLength - 1 - i;
        }
    }


}