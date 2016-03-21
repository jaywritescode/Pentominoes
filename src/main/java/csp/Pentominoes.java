package csp;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Table;

import java.awt.*;
import java.util.Set;

public class Pentominoes {

    final ImmutableSet<Point> squares;

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

        private PentominoShape(Set<Set<Point>> orientations) {
            this.orientations = orientations;
        }
    }

    public Pentominoes(Set<Point> squares) {
        this.squares = ImmutableSet.copyOf(squares);
    }

    public Set<Pentomino> solve() {
        ExactCoverProblem ecp = new ExactCoverProblem();



        return null;
    }

    static class PentominoFactory {

    }


    class Pentomino {
        PentominoShape shape;
        Set<Point> cells;

        Pentomino(PentominoShape shape, Set<Point> cells) {
            this.shape = shape;
            this.cells = cells;
        }
    }

    class Candidate {
        final Set<Point> cells;
        final PentominoShape type;

        Candidate(PentominoShape type, Set<Point> cells) {
            this.type = type;
            this.cells = cells;
        }
    }

    class ShapeIsPresentConstraint {
        final PentominoShape pentomino;

        ShapeIsPresentConstraint(PentominoShape pentomino) {
            this.pentomino = pentomino;
        }
    }

    class ShapeOccupiesSquareConstraint {
        final Point square;

        ShapeOccupiesSquareConstraint(Point square) {
            this.square = square;
        }
    }
}
