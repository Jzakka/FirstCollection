package org.example.hanoi;

public class Solution {
    public int[][] solution(int n) {
        if(n==1){
            return new int[][]{{1, 3}};
        }
        if (n == 2) {
            return new int[][]{{1, 2}, {1, 3}, {2, 3},};
        }
        return null;
    }
}
