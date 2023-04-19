package org.example.meet;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
}

class Points implements Iterable<Point> {
    private final Set<Point> data;

    private Points(Set<Point> data) {
        this.data = data;
    }

    // Point... 는 Point[] 와 같은 뜻
    // Point... 의 특수기능 : 가변인자
    // Points.of(arg1);
    // Points.of(arg1, arg2);
    // Points.of(arg1, arg2, agr3);
    public static Points of(Point... pointArray) {
        // 입력받은 배열을 HashSet 형태로 하다.
        // Collectors.toSet() 를 사용하지 않는 이유 : 우리는 mutable 한것을 원한다.
        // mutable : 수정가능
        // immutable : 수정불가능(add, remove 등이 안됨)
        return new Points(
                Arrays.stream(pointArray)
                        .collect(Collectors.toCollection(HashSet::new))
        );
    }

    public boolean add(Point point) {
        return data.add(point);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Points points)) return false;

        return Objects.equals(data, points.data);
    }

    @Override
    public int hashCode() {
        return data != null ? data.hashCode() : 0;
    }

    @Override
    public Iterator<Point> iterator() {
        return data.iterator();
    }

    public Stream<Point> stream() {
        return data.stream();
    }

    Point getMinPoint() {
        long x = Long.MAX_VALUE;
        long y = Long.MAX_VALUE;

        for (Point point : data) {
            x = Math.min(x, point.x);
            y = Math.min(y, point.y);
        }

        return Point.of(x, y);
    }

    Point getMaxPoint() {
        long x = Long.MIN_VALUE;
        long y = Long.MIN_VALUE;

        for (Point point : data) {
            x = Math.max(x, point.x);
            y = Math.max(y, point.y);
        }

        return Point.of(x, y);
    }

    Points positivePoints() {
        Point minPoint = getMinPoint();

        return Points.of(
                data.stream()
                        .map(p -> Point.of(p.x - minPoint.x, p.y - minPoint.y))
                        .toArray(Point[]::new)
        );
    }

    char[][] emptyMatrix() {
        Point minPoint = getMinPoint();
        Point maxPoint = getMaxPoint();

        int width = (int) (maxPoint.x - minPoint.x + 1);
        int height = (int) (maxPoint.y - minPoint.y + 1);

        char[][] matrix = new char[height][width];

        Arrays.stream(matrix).forEach(row -> Arrays.fill(row, '.'));

        return matrix;
    }

    public char[][] toMatrix() {
        char[][] matrix = emptyMatrix();
        Points points = positivePoints();

        points.forEach(p -> matrix[(int) p.y][(int) p.x] = '*');

        return matrix;
    }
}

class Ut {
    public static IntStream revRange(int from, int to) {
        return IntStream.range(from, to)
                .map(i -> to - i + from - 1);
    }
}