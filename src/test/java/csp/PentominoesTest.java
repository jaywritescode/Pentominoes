package csp;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

import csp.Pentominoes.*;

import static org.junit.Assert.*;

public class PentominoesTest {

    Pentominoes test0, test1, test2;
    String puzzle0, puzzle1, puzzle2;

    public PentominoesTest() throws Exception {
        BufferedReader br;
        String line;
        StringBuilder sb;

        sb = new StringBuilder();
        br = new BufferedReader(new FileReader("puzzles/puz0.txt"));
        while((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        puzzle0 = sb.toString();

        sb = new StringBuilder();
        br = new BufferedReader(new FileReader("puzzles/puz1.txt"));
        while((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        puzzle1 = sb.toString();

        sb = new StringBuilder();
        br = new BufferedReader(new FileReader("puzzles/puz2.txt"));
        while((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        puzzle2 = sb.toString();
    }

    @Before
    public void setUp() {
        test0 = Pentominoes.read(puzzle0);
        test1 = Pentominoes.read(puzzle1);
        test2 = Pentominoes.read(puzzle2);
    }

    @Test
    public void testGetRowCount() {
        assertEquals(7, test2.getRowCount());
    }

    @Test
    public void testGetColumnCount() {
        assertEquals(10, test2.getColumnCount());
    }

    @Test
    public void testSolve() {
        Set<Pentomino> solution = test1.solve();
        assertNotNull(solution);

        for (PentominoShape shape : EnumSet.allOf(PentominoShape.class)) {
            assertEquals(1, countShapesInSet(solution, shape));
        }
        for (Point point : test1.squares) {
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