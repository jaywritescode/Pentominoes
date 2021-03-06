package csp;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A Pentominoes solver.
 */
public class Pentominoes {

    final ImmutableSet<Point> squares;
    public final int rowCount, columnCount;
    Set<Pentomino> solution = null;

    // so we only have to instantiate Points once for the mess of
    // Pentomino enums
    private static Table<Integer, Integer, Point> points;

    static {
        points = HashBasedTable.create();
        for (int row = 0; row < 5; ++row) {
            for (int col = 0; row + col < 5; ++col) {
                points.put(row, col, new Point(row, col));
            }
        }
    }

    enum PentominoShape {
        F(ImmutableSet.<Set<Point>>builder()
                .add(ImmutableSet.of(points.get(1, 0), points.get(2, 0), points.get(0, 1), points.get(1, 1), points.get(1, 2)))
                .add(ImmutableSet.of(points.get(1, 0), points.get(0, 1), points.get(1, 1), points.get(2, 1), points.get(2, 2)))
                .add(ImmutableSet.of(points.get(1, 0), points.get(1, 1), points.get(2, 1), points.get(0, 2), points.get(1, 2)))
                .add(ImmutableSet.of(points.get(0, 0), points.get(0, 1), points.get(1, 1), points.get(2, 1), points.get(1, 2)))
                .add(ImmutableSet.of(points.get(0, 0), points.get(1, 0), points.get(1, 1), points.get(2, 1), points.get(1, 2)))
                .add(ImmutableSet.of(points.get(2, 0), points.get(0, 1), points.get(1, 1), points.get(2, 1), points.get(1, 2)))
                .add(ImmutableSet.of(points.get(1, 0), points.get(0, 1), points.get(1, 1), points.get(1, 2), points.get(2, 2)))
                .add(ImmutableSet.of(points.get(1, 0), points.get(0, 1), points.get(1, 1), points.get(2, 1), points.get(0, 2)))
                .build()),
        I(ImmutableSet.<Set<Point>>builder()
                .add(ImmutableSet.of(points.get(0, 0), points.get(0, 1), points.get(0, 2), points.get(0, 3), points.get(0, 4)))
                .add(ImmutableSet.of(points.get(0, 0), points.get(1, 0), points.get(2, 0), points.get(3, 0), points.get(4, 0)))
                .build()),
        L(ImmutableSet.<Set<Point>>builder()
                .add(ImmutableSet.of(points.get(0, 0), points.get(0, 1), points.get(0, 2), points.get(0, 3), points.get(1, 3)))
                .add(ImmutableSet.of(points.get(0, 0), points.get(1, 0), points.get(2, 0), points.get(3, 0), points.get(0, 1)))
                .add(ImmutableSet.of(points.get(0, 0), points.get(1, 0), points.get(1, 1), points.get(1, 2), points.get(1, 3)))
                .add(ImmutableSet.of(points.get(3, 0), points.get(0, 1), points.get(1, 1), points.get(2, 1), points.get(3, 1)))
                .add(ImmutableSet.of(points.get(1, 0), points.get(1, 1), points.get(1, 2), points.get(0, 3), points.get(1, 3)))
                .add(ImmutableSet.of(points.get(0, 0), points.get(0, 1), points.get(1, 1), points.get(2, 1), points.get(3, 1)))
                .add(ImmutableSet.of(points.get(0, 0), points.get(1, 0), points.get(0, 1), points.get(0, 2), points.get(0, 3)))
                .add(ImmutableSet.of(points.get(0, 0), points.get(1, 0), points.get(2, 0), points.get(3, 0), points.get(3, 1)))
                .build()),
        N(ImmutableSet.<Set<Point>>builder()
                .add(ImmutableSet.of(points.get(0, 0), points.get(0, 1), points.get(1, 1), points.get(1, 2), points.get(1, 3)))
                .add(ImmutableSet.of(points.get(2, 0), points.get(3, 0), points.get(0, 1), points.get(1, 1), points.get(2, 1)))
                .add(ImmutableSet.of(points.get(0, 0), points.get(0, 1), points.get(0, 2), points.get(1, 2), points.get(1, 3)))
                .add(ImmutableSet.of(points.get(0, 1), points.get(1, 0), points.get(1, 1), points.get(2, 0), points.get(3, 0)))
                .add(ImmutableSet.of(points.get(1, 0), points.get(0, 1), points.get(1, 1), points.get(0, 2), points.get(0, 3)))
                .add(ImmutableSet.of(points.get(0, 0), points.get(1, 0), points.get(2, 0), points.get(2, 1), points.get(3, 1)))
                .add(ImmutableSet.of(points.get(1, 0), points.get(1, 1), points.get(0, 2), points.get(1, 2), points.get(0, 3)))
                .add(ImmutableSet.of(points.get(0, 0), points.get(1, 0), points.get(1, 1), points.get(2, 1), points.get(3, 1)))
                .build()),
        P(ImmutableSet.<Set<Point>>builder()
                .add(ImmutableSet.of(points.get(0, 0), points.get(1, 0), points.get(0, 1), points.get(1, 1), points.get(0, 2)))
                .add(ImmutableSet.of(points.get(0, 0), points.get(1, 0), points.get(2, 0), points.get(1, 1), points.get(2, 1)))
                .add(ImmutableSet.of(points.get(1, 0), points.get(0, 1), points.get(1, 1), points.get(0, 2), points.get(1, 2)))
                .add(ImmutableSet.of(points.get(0, 0), points.get(1, 0), points.get(0, 1), points.get(1, 1), points.get(2, 1)))
                .add(ImmutableSet.of(points.get(0, 0), points.get(1, 0), points.get(0, 1), points.get(1, 1), points.get(1, 2)))
                .add(ImmutableSet.of(points.get(1, 0), points.get(2, 0), points.get(0, 1), points.get(1, 1), points.get(2, 1)))
                .add(ImmutableSet.of(points.get(0, 0), points.get(0, 1), points.get(1, 1), points.get(0, 2), points.get(1, 2)))
                .add(ImmutableSet.of(points.get(0, 0), points.get(1, 0), points.get(2, 0), points.get(0, 1), points.get(1, 1)))
                .build()),
        T(ImmutableSet.<Set<Point>>builder()
                .add(ImmutableSet.of(points.get(0, 0), points.get(1, 0), points.get(2, 0), points.get(1, 1), points.get(1, 2)))
                .add(ImmutableSet.of(points.get(2, 0), points.get(0, 1), points.get(1, 1), points.get(2, 1), points.get(2, 2)))
                .add(ImmutableSet.of(points.get(1, 0), points.get(1, 1), points.get(0, 2), points.get(1, 2), points.get(2, 2)))
                .add(ImmutableSet.of(points.get(0, 0), points.get(0, 1), points.get(1, 1), points.get(2, 1), points.get(0, 2)))
                .build()),
        U(ImmutableSet.<Set<Point>>builder()
                .add(ImmutableSet.of(points.get(0, 0), points.get(2, 0), points.get(0, 1), points.get(1, 1), points.get(2, 1)))
                .add(ImmutableSet.of(points.get(0, 0), points.get(1, 0), points.get(0, 1), points.get(0, 2), points.get(1, 2)))
                .add(ImmutableSet.of(points.get(0, 0), points.get(1, 0), points.get(2, 0), points.get(0, 1), points.get(2, 1)))
                .add(ImmutableSet.of(points.get(0, 0), points.get(1, 0), points.get(1, 1), points.get(0, 2), points.get(1, 2)))
                .build()),
        V(ImmutableSet.<Set<Point>>builder()
                .add(ImmutableSet.of(points.get(0, 0), points.get(1, 0), points.get(2, 0), points.get(0, 1), points.get(0, 2)))
                .add(ImmutableSet.of(points.get(0, 0), points.get(1, 0), points.get(2, 0), points.get(2, 1), points.get(2, 2)))
                .add(ImmutableSet.of(points.get(2, 0), points.get(2, 1), points.get(0, 2), points.get(1, 2), points.get(2, 2)))
                .add(ImmutableSet.of(points.get(0, 0), points.get(0, 1), points.get(0, 2), points.get(1, 2), points.get(2, 2)))
                .build()),
        W(ImmutableSet.<Set<Point>>builder()
                .add(ImmutableSet.of(points.get(0, 0), points.get(0, 1), points.get(1, 1), points.get(1, 2), points.get(2, 2)))
                .add(ImmutableSet.of(points.get(0, 2), points.get(1, 1), points.get(1, 2), points.get(2, 0), points.get(2, 1)))
                .add(ImmutableSet.of(points.get(0, 0), points.get(1, 0), points.get(1, 1), points.get(2, 1), points.get(2, 2)))
                .add(ImmutableSet.of(points.get(0, 1), points.get(0, 2), points.get(1, 0), points.get(1, 1), points.get(2, 0)))
                .build()),
        X(ImmutableSet.<Set<Point>>builder()
                .add(ImmutableSet.of(points.get(1, 0), points.get(0, 1), points.get(1, 1), points.get(1, 2), points.get(2, 1)))
                .build()),
        Y(ImmutableSet.<Set<Point>>builder()
                .add(ImmutableSet.of(points.get(1, 0), points.get(0, 1), points.get(1, 1), points.get(1, 2), points.get(1, 3)))
                .add(ImmutableSet.of(points.get(2, 0), points.get(0, 1), points.get(1, 1), points.get(2, 1), points.get(3, 1)))
                .add(ImmutableSet.of(points.get(0, 0), points.get(0, 1), points.get(0, 2), points.get(1, 2), points.get(0, 3)))
                .add(ImmutableSet.of(points.get(0, 0), points.get(1, 0), points.get(2, 0), points.get(3, 0), points.get(1, 1)))
                .add(ImmutableSet.of(points.get(0, 0), points.get(0, 1), points.get(1, 1), points.get(0, 2), points.get(0, 3)))
                .add(ImmutableSet.of(points.get(0, 0), points.get(1, 0), points.get(2, 0), points.get(3, 0), points.get(2, 1)))
                .add(ImmutableSet.of(points.get(1, 0), points.get(1, 1), points.get(0, 2), points.get(1, 2), points.get(1, 3)))
                .add(ImmutableSet.of(points.get(1, 0), points.get(0, 1), points.get(1, 1), points.get(2, 1), points.get(3, 1)))
                .build()),
        Z(ImmutableSet.<Set<Point>>builder()
                .add(ImmutableSet.of(points.get(0, 0), points.get(1, 0), points.get(1, 1), points.get(1, 2), points.get(2, 2)))
                .add(ImmutableSet.of(points.get(2, 0), points.get(0, 1), points.get(1, 1), points.get(2, 1), points.get(0, 2)))
                .add(ImmutableSet.of(points.get(0, 0), points.get(0, 1), points.get(1, 1), points.get(2, 1), points.get(2, 2)))
                .add(ImmutableSet.of(points.get(2, 0), points.get(1, 0), points.get(1, 1), points.get(0, 2), points.get(1, 2)))
                .build());

        Set<Set<Point>> orientations;

        PentominoShape(Set<Set<Point>> orientations) {
            this.orientations = orientations;
        }
    }

    /**
     * Creates a pentominoes puzzle from a set of coordinates.
     *
     * @param squares a set of coordinates. At least one `Point` should have
     *                an x-coordinate of 0, and at least one `Point` should have
     *                a y-coordinate of 0
     */
    public Pentominoes(Set<Point> squares) {
        this.squares = ImmutableSet.copyOf(squares);
        this.rowCount = getRowCount();
        this.columnCount = getColumnCount();
    }

    /**
     * Reads a pentominoes puzzle from a string.
     *
     * Starting at the top row of the puzzle, this method reads each row as a sequence of
     * either space or non-space characters. Non-space characters are squares in the puzzle.
     * The current row terminates when the reader reaches a newline character.
     *
     * @param puzzle the puzzle, as described above
     * @return a new
     */
    public static Pentominoes read(String puzzle) {
        Set<Point> squares = new HashSet();

        int row = 0, column = 0;
        for (char k : puzzle.toCharArray()) {
            if (k == '\n') {
                ++row;
                column = 0;
            }
            else if (k == ' ') {
                ++column;
            }
            else {
                squares.add(new Point(row, column));
                ++column;
            }
        }

        return new Pentominoes(squares);
    }

    /**
     * Solves the puzzle.
     *
     * @return a set of Pentominoes, or <code>null</code> if there is no solution
     */
    public Set<Pentomino> solve() {
        if (solution != null) {
            return solution;
        }

        return solution = new ExactCoverProblem<Pentomino, PentominoConstraint>(makeCandidates(), makeConstraints()) {
            @Override
            public boolean relation(PentominoConstraint constraint, Pentomino candidate) {
                return constraint.isSatisfiedBy(candidate);
            }
        }.solve();
    }

    /**
     * Prints the solution.
     */
    public void print() {
        if (solution == null) {
            solve();
        }
        
        String[][] k = new String[columnCount + 1][rowCount + 1];
        solution.forEach(pentomino -> {
            pentomino.cells.forEach(point -> k[point.y][point.x] = pentomino.shape.toString());
        });

        StringBuilder sb = new StringBuilder();
        for (int row = 0; row <= columnCount; ++row) {
            for (int column = 0; column <= rowCount; ++column) {
                if (k[row][column] == null) {
                    sb.append(" ");
                }
                else {
                    sb.append(k[row][column]);
                }
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private Set<PentominoConstraint> makeConstraints() {
        return Sets.union(
                EnumSet.allOf(PentominoShape.class)
                        .stream()
                        .map(shape -> new ShapeIsPresentConstraint(shape))
                        .collect(Collectors.toSet()),
                squares
                        .stream()
                        .map(square -> new ShapeOccupiesSquareConstraint(square))
                        .collect(Collectors.toSet())
        );
    }

    private Set<Pentomino> makeCandidates() {
        return EnumSet
                .allOf(PentominoShape.class)
                .stream()
                .flatMap(shape -> makeCandidatesForShape(shape).stream())
                .collect(Collectors.toSet());
    }

    private Set<Pentomino> makeCandidatesForShape(PentominoShape shape) {
        return shape.orientations.stream()
                .flatMap(orientation -> {
                    Set<Set<Point>> translations = translateAcrossSquares(orientation);
                    return translations.stream().map(points -> new Pentomino(shape, points));
                }).collect(Collectors.toSet());
    }

    private Set<Set<Point>> translateAcrossSquares(Set<Point> points) {
        Set<Set<Point>> t = new HashSet();
        Set<Point> p;

        for (int i = 0; i <= rowCount; ++i) {
            for (int j = 0; j <= columnCount; ++j) {
                p = translate(points, i, j);
                if (squares.containsAll(p)) {
                    t.add(p);
                }
            }
        }
        return t;
    }

    private static Set<Point> translate(Set<Point> points, final int dx, final int dy) {
        return points.stream().map(point -> new Point(point.x + dx, point.y + dy)).collect(Collectors.toSet());
    }

    int getRowCount() {
        return this.squares.stream().max(Comparator.comparingInt((p) -> p.x)).get().x;
    }

    int getColumnCount() {
        return this.squares.stream().max(Comparator.comparingInt((p) -> p.y)).get().y;
    }

    class Pentomino {
        PentominoShape shape;
        Set<Point> cells;

        Pentomino(PentominoShape shape, Set<Point> cells) {
            this.shape = shape;
            this.cells = cells;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pentomino pentomino = (Pentomino) o;
            return shape == pentomino.shape &&
                    Objects.equals(cells, pentomino.cells);
        }

        @Override
        public int hashCode() {
            return Objects.hash(shape, cells);
        }
    }

    abstract class PentominoConstraint {
        public abstract boolean isSatisfiedBy(Pentomino pentomino);
    }

    class ShapeIsPresentConstraint extends PentominoConstraint {
        final PentominoShape shape;

        ShapeIsPresentConstraint(PentominoShape shape) {
            this.shape = shape;
        }

        public boolean isSatisfiedBy(Pentomino pentomino) {
            return pentomino.shape == shape;
        }
    }

    class ShapeOccupiesSquareConstraint extends PentominoConstraint {
        final Point square;

        ShapeOccupiesSquareConstraint(Point square) {
            this.square = square;
        }

        public boolean isSatisfiedBy(Pentomino pentomino) {
            return pentomino.cells.contains(square);
        }
    }

    public static void main(String... args) {
        StringBuilder sb = new StringBuilder();
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader("puzzles/puz1.txt"));

            while ((line = br.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Pentominoes pentominoes = Pentominoes.read(sb.toString());
        pentominoes.solve();
        pentominoes.print();
    }
}
