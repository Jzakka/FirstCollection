package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    Solution s = new Solution();

    @Test
    void test() {
        int[][] line = {
                {2, -1, 4},
                {-2, -1, 4},
                {0, -1, 1},
                {5, -8, -12},
                {5, 8, 12}
        };
        Assertions.assertThat(s.solution(line))
                .containsExactly("....*....", ".........", ".........", "*.......*", ".........", ".........", ".........", ".........", "*.......*");
    }

    @Test
    void test2() {
        int[][] line = {
                {0, 1, -1},
                {1, 0, -1},
                {1, 0, 1},
        };
        Assertions.assertThat(s.solution(line))
                .containsExactly("*.*");

    }

    @Test
    void test3() {
        int[][] line = {
                {1, -1, 0},
                {2, -1, 0},
        };
        Assertions.assertThat(s.solution(line))
                .containsExactly("*");

    }

    @Test
    void test4() {
        int[][] line = {
                {1, -1, 0},
                {2, -1, 0},
                {4, -1, 0},
        };
        Assertions.assertThat(s.solution(line))
                .containsExactly("*");

    }
}