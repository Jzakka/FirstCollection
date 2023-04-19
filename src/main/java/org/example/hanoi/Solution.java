package org.example.hanoi;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int[][] solution(int n) {
        return new Hanoi(1, 3, n).toArray();
    }

    public static class Hanoi {
        private final int FROM;
        private final int TO;
        private final int n;
        private final List<int[]> paths = new ArrayList<>();

        public Hanoi(int from, int to, int n) {
            this.FROM = from;
            this.TO = to;
            this.n = n;

            calculate();
        }

        private void calculate() {
            if (n == 1) {
                addPath(1, 3);
            }
            if (FROM == 1 && TO == 2 && n == 2) {
                addPath(1, 3);
                addPath(1, 2);
                addPath(3, 2);
            }
            if (FROM == 1 && TO == 3 && n == 2) {
                addPath(1, 2);
                addPath(1, 3);
                addPath(2, 3);
            }
            if (FROM == 2 && TO == 1 && n == 2) {
                addPath(2, 3);
                addPath(2, 1);
                addPath(3, 1);
            }
            if (FROM == 2 && TO == 3 && n == 2) {

            }
            if (FROM == 3 && TO == 1 && n == 2) {

            }
            if (FROM == 3 && TO == 2 && n == 2) {

            }
        }

        private void addPath(int from, int to) {
            paths.add(new int[]{from, to});
        }

        public int[][] toArray() {
            return paths.toArray(int[][]::new);
        }
    }
}

