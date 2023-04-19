package org.example;

import org.assertj.core.api.Assertions;
import org.example.hanoi.Solution;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HanoiTest {

    @Test
    void t01() {
        Assertions.assertThat(new Solution().solution(1)).isEqualTo(
                new int[][]{{1, 3}}
        );
    }

    @Test
    void t02() {
        Assertions.assertThat(new Solution().solution(2)).isEqualTo(
                new int[][]{{1, 2}, {1, 3}, {2, 3},}
        );
    }

}