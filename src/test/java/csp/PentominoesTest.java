package csp;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

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
        this.add(new Point(4, 0));
        this.add(new Point(5, 0));
        this.add(new Point(3, 1));
        this.add(new Point(4, 1));
        this.add(new Point(5, 1));
        this.add(new Point(6, 1));
        this.add(new Point(11, 1));
        this.add(new Point(2, 2));
        this.add(new Point(3, 2));
        this.add(new Point(4, 2));
        this.add(new Point(5, 2));
        this.add(new Point(6, 2));
        this.add(new Point(7, 2));
        this.add(new Point(8, 2));
        this.add(new Point(11, 2));
        this.add(new Point(1, 3));
        this.add(new Point(2, 3));
        this.add(new Point(3, 3));
        this.add(new Point(4, 3));
        this.add(new Point(5, 3));
        this.add(new Point(6, 3));
        this.add(new Point(7, 3));
        this.add(new Point(8, 3));
        this.add(new Point(10, 3));
        this.add(new Point(11, 3));
        this.add(new Point(0, 4));
        this.add(new Point(1, 4));
        this.add(new Point(2, 4));
        this.add(new Point(3, 4));
        this.add(new Point(4, 4));
        this.add(new Point(5, 4));
        this.add(new Point(6, 4));
        this.add(new Point(7, 4));
        this.add(new Point(8, 4));
        this.add(new Point(9, 4));
        this.add(new Point(10, 4));
        this.add(new Point(11, 4));
        this.add(new Point(1, 5));
        this.add(new Point(2, 5));
        this.add(new Point(3, 5));
        this.add(new Point(4, 5));
        this.add(new Point(5, 5));
        this.add(new Point(6, 5));
        this.add(new Point(7, 5));
        this.add(new Point(8, 5));
        this.add(new Point(10, 5));
        this.add(new Point(11, 5));
        this.add(new Point(2, 6));
        this.add(new Point(3, 6));
        this.add(new Point(4, 6));
        this.add(new Point(5, 6));
        this.add(new Point(6, 6));
        this.add(new Point(7, 6));
        this.add(new Point(11, 6));
        this.add(new Point(2, 7));
        this.add(new Point(3, 7));
        this.add(new Point(4, 7));
        this.add(new Point(4, 8));
        this.add(new Point(5, 8));
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
    public void testConstraints() {
        test1.solve();
    }
}