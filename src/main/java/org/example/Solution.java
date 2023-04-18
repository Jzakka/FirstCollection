package org.example;

import java.util.*;
import java.util.stream.IntStream;

public class Solution {
    Meets meets;

    public String[] solution(int[][] line) {
        meets = new Meets(line);

        return meets.coordinate();
    }

    static class Meets implements Iterable<Meet> {
        private Set<Meet> meets;

        public Meets(int[][] line) {
            this.meets = intMeets(line);
        }

        private Set<Meet> intMeets(int[][] line) {
            Set<Meet> meets = new HashSet<>();
            for (int i = 0; i < line.length; i++) {
                for (int j = i + 1; j < line.length; j++) {
                    Meet meet = getMeet(line[i], line[j]);
                    if (meet != null) {
                        meets.add(meet);
                    }
                }
            }
            return meets;
        }

        private Meet getMeet(int[] line1, int[] line2) {
            long a, b, c, d, e, f;
            a = line1[0];
            b = line1[1];
            c = line2[0];
            d = line2[1];
            e = line1[2];
            f = line2[2];


            long parent = a * d - b * c;
            if (parent == 0) {
                return null;
            }

            long xChild = b * f - e * d;
            long yChild = e * c - a * f;

            if (xChild % parent != 0 || yChild % parent != 0) {
                return null;
            }

            return new Meet((int) (xChild / parent), (int) (yChild / parent));
        }

        public String[] coordinate() {
            int[] area = getField(this);

            int xOffset = area[0];
            int yOffset = area[1];
            int xLimit = area[2];
            int yLimit = area[3];
            int height = yOffset - yLimit;
            int width = xLimit - xOffset;
            String[] res = new String[height + 1];
            StringBuilder sb = new StringBuilder();

            for (int y = 0; y <= height; y++) {
                for (int x = 0; x <= width; x++) {
                    print(xOffset, yOffset, y, sb, x);
                }
                res[y] = sb.toString();
                sb = new StringBuilder();
            }

            return res;
        }

        private void print(int xOffset, int yOffset, int y, StringBuilder sb, int x) {
            int originX = x + xOffset;
            int originY = -y + yOffset;
            if (has(new Meet(originX, originY))) {
                sb.append('*');
                return;
            }
            sb.append('.');
        }

        public boolean has(Meet meet) {
            return meets.contains(meet);
        }

        public int[] getField(Meets meets) {
            int xOffset = Integer.MAX_VALUE;
            int yOffset = Integer.MIN_VALUE;
            int xLimit = Integer.MIN_VALUE;
            int yLimit = Integer.MAX_VALUE;

            for (Meet meet : meets) {
                xOffset = Math.min(xOffset, meet.x);
                yOffset = Math.max(yOffset, meet.y);
                xLimit = Math.max(xLimit, meet.x);
                yLimit = Math.min(yLimit, meet.y);
            }

            return new int[]{xOffset, yOffset, xLimit, yLimit};
        }

        @Override
        public Iterator<Meet> iterator() {
            return meets.iterator();
        }
    }

    static class Meet {
        int x;
        int y;

        public Meet(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Meet meet = (Meet) o;
            return x == meet.x && y == meet.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}