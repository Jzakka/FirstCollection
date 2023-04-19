package org.example;

import org.assertj.core.api.Assertions;
import org.example.hanoi.Solution;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HanoiTest {

    @Test
    @DisplayName("n==1 => {{1,3}}")
    void t01() {
        Assertions.assertThat(new Solution().solution(1)).isEqualTo(
                new int[][]{{1, 3}}
        );
    }

    @Test
    @DisplayName("n==2 => {{1, 2}, {1, 3}, {2, 3},}")
    void t02() {
        Assertions.assertThat(new Solution().solution(2)).isEqualTo(
                new int[][]{{1, 2}, {1, 3}, {2, 3},}
        );
    }

    @Test
    @DisplayName("from==1 && to==2 && n==2 => {{1, 3}, {1, 2}, {3, 2}}")
    void t03() {
        Assertions.assertThat(
                new Solution.Hanoi(1, 2, 2).toArray()
        ).isEqualTo(
                new int[][]{{1, 3}, {1, 2}, {3, 2}}
        );
    }

    @Test
    @DisplayName("from==2 && to==1 && n==2 => {{2, 3}, {2, 1}, {3, 1}}")
    void t04() {
        Assertions.assertThat(
                new Solution.Hanoi(2, 1, 2).toArray()
        ).isEqualTo(
                new int[][] {{2, 3}, {2, 1}, {3, 1}}
        );
    }
}