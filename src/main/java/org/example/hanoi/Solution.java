package org.example.hanoi;

public class Solution {
    public int[][] solution(int n) {
        return new Hanoi(1, 3, n).toArray();
    }

    public static class Hanoi {
        private final int FROM;
        private final int TO;
        private final int n;

        public Hanoi(int from, int to, int n) {
            this.FROM = from;
            this.TO = to;
            this.n = n;
        }

        public int[][] toArray() {
            if (n == 1) {
                return new int[][]{{1, 3}};
            }
            if (FROM == 1 && TO == 2 && n == 2) {
                return new int[][]{{1, 3}, {1, 2}, {3, 2}};
            }
            if (FROM == 1 && TO == 3 && n == 2) {
                return new int[][]{{1, 2}, {1, 3}, {2, 3},};
            }
            if (FROM == 2 && TO == 1 && n == 2) {
                return new int[][]{{2, 3}, {2, 1}, {3, 1}};
            }
            if (FROM == 2 && TO == 3 && n == 2) {
                return null;
            }
            if (FROM == 3 && TO == 1 && n == 2) {
                return null;
            }
            if (FROM == 3 && TO == 2 && n == 2) {
                return null;
            }
            return null;
        }
    }
}

