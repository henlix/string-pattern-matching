package org.sopt.string.strategy.impl;

import org.sopt.string.domain.Result;
import org.sopt.string.domain.ResultSet;
import org.sopt.string.strategy.base.Strategy;

/***
 *
 * Rabin-Karp Algorithm : Reduce time for comparison by hashing.
 * Ref. http://blog.naver.com/impress0503/220200612600
 *
 */
public class RK implements Strategy {

    private static final int COEFFICIENT = 2;
    private static final int MAX_VALUE = Integer.MAX_VALUE;

    /**
     *
     * hash 메서드로부터 입력에 대한 해시 결과를 추출하고 비교하여 결과를 반환합니다.
     *
     * @param from 검색의 대상이 되는 임의의 문자열
     * @param target 검색하고자 하는 내용
     * @return 검색 결과
     *
     */
    @Override
    public ResultSet find(String from, String target) {

        ResultSet set = new ResultSet();

        final int tLength = target.length();
        final int tHash = hash(target, 0, tLength);

        for (int i = 0; i < from.length() - target.length() + 1; i++) {

            int sHash = hash(from, i, tLength);

            if (tHash == sHash)
                set.add(new Result(i, i + tLength));
        }

        return set;
    }

    /**
     *
     * 특정 문자열에 대해, 초항 여부인지에 따라서 분기하여 해시를 얻어냅니다.
     *
     * @param str 대상 문자열
     * @param i 시작 위치
     * @param m 찾고자 하는 문자열의 길이
     * @return 해시 결과
     *
     */
    private int hash(String str, int i, int m) {

        return i == 0 ? hashInitial(str, i, m) : hashOthers(str, i, m);
    }

    /**
     *
     * 초항에 대한 해시 결과를 계산합니다.
     *
     * @param str 대상 문자열
     * @param i 시작 위치
     * @param m 찾고자 하는 문자열의 길이
     * @return 해시 결과
     *
     */
    private int hashInitial(String str, int i, int m) {

        int hash = 0;

        for (int x = i; x < m; x++)
            hash += (int) (str.charAt(x) * Math.pow(COEFFICIENT, m - x - 1));

        return hash % MAX_VALUE;
    }

    /**
     *
     * 초항이 아닌 값에 대해서 빠르게 연산할 수 있도록 재귀 연산을 합니다.
     *
     * @param str 대상 문자열
     * @param i 시작 위치
     * @param m 찾고자 하는 문자열의 길이
     * @return 해시 결과
     *
     */
    private int hashOthers(String str, int i, int m) {

        // 라빈-카프 알고리즘에서는 해시에 대한 점화식을 기본으로 이전 문자열의 값과 바로 아랫 단계의 값을 가지고 빠르게 계산합니다.
        return (int) (2 * (hash(str, i - 1, m) - str.charAt(i - 1) * Math.pow(COEFFICIENT, m - 1)) + str.charAt(i + m - 1));
    }
}