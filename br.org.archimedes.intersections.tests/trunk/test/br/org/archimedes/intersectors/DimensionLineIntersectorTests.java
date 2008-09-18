
package br.org.archimedes.intersectors;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import br.org.archimedes.Tester;
import br.org.archimedes.dimension.Dimension;
import br.org.archimedes.exceptions.InvalidArgumentException;
import br.org.archimedes.exceptions.NullArgumentException;
import br.org.archimedes.intersections.interfaces.Intersector;
import br.org.archimedes.line.Line;
import br.org.archimedes.mock.MockFont;
import br.org.archimedes.model.Point;

public class DimensionLineIntersectorTests extends Tester {

    Dimension testDimension;

    Intersector intersector;


    @Before
    public void setUp () throws NullArgumentException, InvalidArgumentException {
        MockFont mockFont = new MockFont("fonts/arial.ttf", 100);
        testDimension = new Dimension(new Point( -100.0, 0.0), new Point(100.0,
                0.0), -30.0, 12.0, mockFont);
        intersector = new DimensionLineIntersector();
    }

    @Test
    public void testNullArguments () throws NullArgumentException,
            InvalidArgumentException {

        Line testLine = new Line(new Point(0.0, 0.0), new Point(1.0, 1.0));
        try {
            intersector.getIntersections(testLine, null);
            fail("The otherElement is null and getIntersections should have thrown a NullArgumentException");
        }
        catch (NullArgumentException e) {
            // Passed
        }

        try {
            intersector.getIntersections(null, testLine);
            fail("The element is null and getIntersections should have thrown a NullArgumentException");
        }
        catch (NullArgumentException e) {
            // Passed
        }

        try {
            intersector.getIntersections(testDimension, null);
            fail("The otherElement is null and getIntersections should have thrown a NullArgumentException");
        }
        catch (NullArgumentException e) {
            // Passed
        }

        try {
            intersector.getIntersections(null, testDimension);
            fail("The element is null and getIntersections should have thrown a NullArgumentException");
        }
        catch (NullArgumentException e) {
            // Passed
        }

        try {
            intersector.getIntersections(null, null);
            fail("Both elements are null and getIntersections should have thrown a NullArgumentException");
        }
        catch (NullArgumentException e) {
            // Passed
        }
    }

    @Test
    public void testNoIntersections () throws NullArgumentException,
            InvalidArgumentException {

        Line testLine = new Line(new Point( -5.0, 100.0), new Point(5.0, 100.0));
        assertCollectionTheSame(Collections.emptyList(), intersector
                .getIntersections(testDimension, testLine));
    }

    @Test
    public void testSingleLineIntersection () throws NullArgumentException,
            InvalidArgumentException {

        Line testLine = new Line(new Point( -150.0, -15.0), new Point( -99.0,
                -15.0));
        Collection<Point> intersections = new ArrayList<Point>();
        intersections.add(new Point( -100.0, -15.0));

        assertCollectionTheSame(intersections, intersector.getIntersections(
                testLine, testDimension));
    }

    @Test
    public void testCrossingLinesIntersection () throws NullArgumentException,
            InvalidArgumentException {

        Line testLine = new Line(new Point( -110.0, -40.0), new Point( -90.0,
                -20.0));
        Collection<Point> intersections = new ArrayList<Point>();
        intersections.add(new Point( -100.0, -30.0));

        assertCollectionTheSame(intersections, intersector.getIntersections(
                testLine, testDimension));
    }

    @Test
    public void testTextIntersection () throws NullArgumentException,
            InvalidArgumentException {

        Line testLine = new Line(new Point(0.0, 0.0), new Point(0.0, -19.0));

        assertCollectionTheSame(Collections.singleton(new Point(0.0, -8.0)),
                intersector.getIntersections(testLine, testDimension));
    }

    @Test
    public void testTwoLinesIntersection () throws NullArgumentException,
            InvalidArgumentException {

        Line testLine = new Line(new Point(80.0, -40.0),
                new Point(110.0, -10.0));
        Collection<Point> intersections = new ArrayList<Point>();
        intersections.add(new Point(90.0, -30.0));
        intersections.add(new Point(100.0, -20.0));

        assertCollectionTheSame(intersections, intersector.getIntersections(
                testLine, testDimension));
    }

    @Test
    public void testInfiniteIntersections () throws NullArgumentException,
            InvalidArgumentException {

        Line testLine = new Line(new Point(0.0, -30.0), new Point(110.0, -30.0));
        Collection<Point> intersections = new ArrayList<Point>();
        intersections.add(new Point(100.0, -30.0));

        assertCollectionTheSame(intersections, intersector.getIntersections(
                testLine, testDimension));
    }
}
