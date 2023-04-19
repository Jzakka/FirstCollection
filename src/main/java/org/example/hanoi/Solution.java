package org.example.hanoi;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int[][] solution(int n) {
        return new Hanoi(1, 3, n).toArray();
    }

    public static class Hanoi {
        private final List<int[]> paths = new ArrayList<>();

        public Hanoi(int from, int to, int n) {


            calculate(from,to,n);
        }

        private void calculate(int from, int to, int n) {
            if (n == 1) {
                addPath(from, to);
                return;
            }

            int empty = 6 - (from + to);

            calculate(from, empty, n - 1);
            calculate(from, to, 1);
            calculate(empty, to, n-1);
        }

        private void addPath(int from, int to) {
            paths.add(new int[]{from, to});
        }

        public int[][] toArray() {
            return paths.toArray(int[][]::new);
        }
    }
}

