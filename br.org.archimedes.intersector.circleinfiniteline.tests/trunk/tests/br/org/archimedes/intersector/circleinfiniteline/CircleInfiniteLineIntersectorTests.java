package br.org.archimedes.intersector.circleinfiniteline;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import br.org.archimedes.Tester;
import br.org.archimedes.circle.Circle;
import br.org.archimedes.exceptions.InvalidArgumentException;
import br.org.archimedes.exceptions.NullArgumentException;
import br.org.archimedes.interfaces.Intersector;

import br.org.archimedes.infiniteline.InfiniteLine;
import br.org.archimedes.model.Point;

public class CircleInfiniteLineIntersectorTests extends Tester {
	Intersector intersector;
	Circle testCircle;

	@Before
	public void setUp() throws NullArgumentException, InvalidArgumentException {
		intersector = new CircleInfiniteLineIntersector();
		testCircle = new Circle(new Point(0.0, 0.0), 5.0);
	}

	@Test
	public void testNullElements() throws NullArgumentException,
			InvalidArgumentException {
		InfiniteLine testInfiniteLine = new InfiniteLine(new Point(0.0, 0.0),
				new Point(1.0, 1.0));
		try {
			intersector.getIntersections(testInfiniteLine, null);
			fail("The otherElement is null and getIntersections should have thrown a NullArgumentException");
		} catch (NullArgumentException e) {
			// Passed
		}

		try {
			intersector.getIntersections(null, testInfiniteLine);
			fail("The element is null and getIntersections should have thrown a NullArgumentException");
		} catch (NullArgumentException e) {
			// Passed
		}

		try {
			intersector.getIntersections(testCircle, null);
			fail("The otherElement is null and getIntersections should have thrown a NullArgumentException");
		} catch (NullArgumentException e) {
			// Passed
		}

		try {
			intersector.getIntersections(null, testCircle);
			fail("The element is null and getIntersections should have thrown a NullArgumentException");
		} catch (NullArgumentException e) {
			// Passed
		}

		try {
			intersector.getIntersections(null, null);
			fail("Both elements are null and getIntersections should have thrown a NullArgumentException");
		} catch (NullArgumentException e) {
			// Passed
		}
	}

	@Test
	public void testNoIntersections() throws NullArgumentException,
			InvalidArgumentException {
		InfiniteLine testInfiniteLine = new InfiniteLine(new Point(-10.0, 10.0),
				new Point(10.0, 10.0));
		Collection<Point> intersections = intersector.getIntersections(
				testCircle, testInfiniteLine);
		assertCollectionTheSame(new ArrayList<Point>(), intersections);
	}

	@Test
	public void testTangentInfiniteLine() throws NullArgumentException,
			InvalidArgumentException {
		InfiniteLine testInfiniteLine = new InfiniteLine(new Point(5.0, -5.0),
				new Point(5.0, -4.0));
		Collection<Point> expected = new ArrayList<Point>();
		expected.add(new Point(5.0, 0.0));
		assertCollectionTheSame(expected, intersector.getIntersections(
				testCircle, testInfiniteLine));
	}

	@Test
	public void testTwoIntersections() throws NullArgumentException,
			InvalidArgumentException {
		InfiniteLine testInfiniteLine = new InfiniteLine(new Point(-1.0, 0.0),
				new Point(1.0, 0.0));
		Collection<Point> expected = new ArrayList<Point>();
		expected.add(new Point(-5.0, 0.0));
		expected.add(new Point(5.0, 0.0));
		assertCollectionTheSame(expected, intersector.getIntersections(
				testCircle, testInfiniteLine));
	}

}
