package csp;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.*;

import csp.Pentominoes.*;

import static org.junit.Assert.*;

public class PentominoesTest {

    Pentominoes test1, test2;

    Set<Point> squares1 = new HashSet() {{
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if ((i == 3 || i == 4) && (j == 3 || j == 4)) {
                    continue;
                }
                this.add(new Point(i, j));
            }
        }
    }};
    Set<Point> squares2 = new HashSet() {{
        this.add(new Point(0, 4));
        this.add(new Point(0, 5));
        this.add(new Point(0, 6));
        this.add(new Point(0, 7));
        this.add(new Point(1, 2));
        this.add(new Point(1, 3));
        this.add(new Point(1, 4));
        this.add(new Point(1, 5));
        this.add(new Point(1, 6));
        this.add(new Point(1, 7));
        this.add(new Point(1, 8));
        this.add(new Point(1, 9));
        this.add(new Point(2, 1));
        this.add(new Point(2, 2));
        this.add(new Point(2, 3));
        this.add(new Point(2, 4));
        this.add(new Point(2, 5));
        this.add(new Point(2, 6));
        this.add(new Point(2, 7));
        this.add(new Point(2, 8));
        this.add(new Point(2, 9));
        this.add(new Point(2, 10));
        this.add(new Point(3, 1));
        this.add(new Point(3, 2));
        this.add(new Point(3, 3));
        this.add(new Point(3, 4));
        this.add(new Point(3, 5));
        this.add(new Point(3, 6));
        this.add(new Point(3, 7));
        this.add(new Point(3, 8));
        this.add(new Point(3, 9));
        this.add(new Point(3, 10));
        this.add(new Point(4, 0));
        this.add(new Point(4, 1));
        this.add(new Point(4, 2));
        this.add(new Point(4, 3));
        this.add(new Point(4, 4));
        this.add(new Point(4, 5));
        this.add(new Point(4, 6));
        this.add(new Point(4, 7));
        this.add(new Point(4, 8));
        this.add(new Point(4, 9));
        this.add(new Point(4, 10));
        this.add(new Point(4, 11));
        this.add(new Point(5, 0));
        this.add(new Point(5, 1));
        this.add(new Point(5, 2));
        this.add(new Point(5, 3));
        this.add(new Point(5, 8));
        this.add(new Point(5, 9));
        this.add(new Point(5, 10));
        this.add(new Point(5, 11));
        this.add(new Point(6, 0));
        this.add(new Point(6, 1));
        this.add(new Point(6, 2));
        this.add(new Point(6, 3));
        this.add(new Point(6, 8));
        this.add(new Point(6, 9));
        this.add(new Point(6, 10));
        this.add(new Point(6, 11));
    }};

    @Before
    public void setUp() {
        test1 = new Pentominoes(squares1);
        test2 = new Pentominoes(squares2);
    }

    @Test
    public void testGetWidth() {
        assertEquals(11, test2.getRightmost());
    }

    @Test
    public void testGetHeight() {
        assertEquals(7, test1.getTopmost());
    }

    @Test
    public void testSolve() {
        Set<Pentomino> solution = test2.solve();

        for (PentominoShape shape : EnumSet.allOf(PentominoShape.class)) {
            assertEquals(1, countShapesInSet(solution, shape));
        }
        for (Point point : test2.squares) {
            assertEquals(1, countSquaresUsedInSet(solution, point));
        }
    }

    private long countShapesInSet(Set<Pentomino> set, final PentominoShape shape) {
        return set.stream().filter(p -> p.shape == shape).count();
    }

    private long countSquaresUsedInSet(Set<Pentomino> set, final Point point) {
        return set.stream().filter(p -> p.cells.contains(point)).count();
    }
}