package org.example.meet;

public class TeacherSolution {
    public String[] solution(int[][] line) {
        // 교점들 구하고
        Points points = intersections(line);
        // 매트릭스로 옮긴다.
        char[][] matrix = points.toMatrix();

        return drawOnCoordinate(matrix);
    }

    public Point intersection(int[] line1, int[] line2) {
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
