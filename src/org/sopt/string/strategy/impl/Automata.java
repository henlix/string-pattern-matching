package org.sopt.string.strategy.impl;

import org.sopt.string.domain.Result;
import org.sopt.string.domain.ResultSet;
import org.sopt.string.strategy.base.Strategy;

public class Automata implements Strategy {

    public Automata(){//Default constructor
        alphabetSet = new char[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'
                ,' ','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','!','.',','};
    }
    public Automata(char[] alphabetSet){
        this.alphabetSet = alphabetSet;
    }
    public void setAlphabetSet(char[] alphabetSet){
        this.alphabetSet = alphabetSet;
    }
    public char[] getAlphabetSet(){
        return alphabetSet;
    }

    @Override
    public ResultSet find(String from, String target) {
        ResultSet set = new ResultSet();
        if(alphabetSet == null)
            throw new UnsupportedOperationException("Please choose alphabet set");
        ConstructAutomata(target);
        int patternLength = target.length();
        int textLength = from.length();
        currentState = 0;
        for(int i = 0; i < textLength ; i++)
        {
            currentState = transitionTable[currentState][charToIndex(from.charAt(i))];
            if(currentState == finalState)
                //System.out.println(i-patternLength +" ~ "+i);
                set.add(new Result(i-patternLength,i));
        }

        return set;
    }

    public boolean ConstructAutomata(String pattern){
        transitionTable = new int[pattern.length()+1][alphabetSet.length];
        // transitionTable[현제 state값][알파벳index값]
        finalState = pattern.length();
        for(int q = 0; q <= finalState; q++)
        {
            for(int i=0; i < alphabetSet.length ; i++)
            {
                char a = alphabetSet[i];
                int k = (finalState+1) < (q+2) ? (finalState+1) : (q+2);
                do{
                    k--;
                }while(!isSuffix(pattern.substring(0,k), pattern.substring(0,q) + Character.toString(a) ));
                transitionTable[q][i] = k;
            }
        }
        return true;
    }
    public boolean isSuffix(String subsequence, String target){// param1이 param2의 suffix인지 확인하는 함수
        int m = target.length();
        int n = subsequence.length();
        if(n>m)
            return false;
        String comp = target.substring(m-n);
        return comp.equals(subsequence);
    }
    public int charToIndex(char ch)
    {
        for(int i=0 ; i < alphabetSet.length ; i++)
        {
            if(ch == alphabetSet[i])
                return i;
        }
        throw new UnsupportedOperationException("Charset sequence is not Compatible ("+ch+")");
    }

    private char[] alphabetSet;//사용되는 문자열 종류의 배열을 갖는다.
    private int[][] transitionTable;//오토마타 수행에 사용되는 전이테이블
    private int currentState, finalState;//오토마타 수행에 사용되는 현제 state번호와 final state번호
}