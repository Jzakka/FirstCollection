package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
}

class TeacherSolution {
    public String[] solution(int[][] line) {
        // 교점들 구하고
        Points points = intersections(line);
        // 매트릭스로 옮긴다.
        char[][] matrix = points.toMatrix();

        return drawOnCoordinate(matrix);
    }

    Point intersection(int[] line1, int[] line2) {
        // Ax + By + E = 0
        double A = line1[0];
        double B = line1[1];
        double E = line1[2];

        // Cx + Dy + F = 0
        double C = line2[0];
        double D = line2[1];
        double F = line2[2];

        double divisor = A * D - B * C;

        // 아래와 같은 경우 평행해서 교점이 없다.
        if (divisor == 0) return null;

        double x = (B * F - E * D) / divisor;
        double y = (E * C - A * F) / divisor;

        // 문제에서 정수좌표만 구하라고 했다.
        if (x != (long) x) return null;
        // 문제에서 정수좌표만 구하라고 했다.
        if (y != (long) y) return null;

        return Point.of(x, y);
    }

    Points intersections(int[][] line) {
        Points points = Points.of();

        for (int i = 0; i < line.length; i++) {
            for (int j = i + 1; j < line.length; j++) {
                int[] line1 = line[i];
                int[] line2 = line[j];

                Point point = intersection(line1, line2);

                if (point != null) points.add(point);
            }
        }

        return points;
    }

    String[] drawOnCoordinate(char[][] matrix) {
        return Ut.revRange(0, matrix.length)
                .boxed()
                .map(i -> matrix[i])
                .map(row -> new String(row))
                .toArray(String[]::new);
    }
}

class Point {
    public final long x;
    public final long y;

    private Point(long x, long y) {
        this.x = x;
        this.y = y;
    }

    // 정수로 세팅하는 용도
    public static Point of(long x, long y) {
        return new Point(x, y);
    }

    // 실수로 세팅하는 용도
    public static Point of(double x, double y) {
        return of((long) x, (long) y);
    }

    // 객체 비교, 가독성 좋음
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    // 객체 비교, 객체로 부터 고유키를 뽑아낸다.(int), 대량비교 좋음, 가독성 나쁨
    @Override
    public int hashCode() {
        int result = (int) (x ^ (x >>> 32));
        result = 31 * result + (int) (y ^ (y >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
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